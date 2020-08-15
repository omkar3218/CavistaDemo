package com.omkar.cavistademo.ui.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.omkar.cavistademo.R;
import com.omkar.cavistademo.databinding.ActivityImageDetailsBinding;
import com.omkar.cavistademo.ui.viewmodel.ImageDetailsViewModel;
import com.omkar.cavistademo.ui.viewmodel.ViewModelFactory;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ImageDetailsActivity extends AppCompatActivity {

    private String imageId;
    private String imageUrl;
    private String imageTitle;
    private ActivityImageDetailsBinding imageDetailsBinding;

    @Inject
    ViewModelFactory viewModelFactory;


    ImageDetailsViewModel imageDetailsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        imageDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        imageDetailsViewModel = viewModelFactory.create(ImageDetailsViewModel.class);
        imageId = getIntent().getStringExtra("image_id");
        imageTitle = getIntent().getStringExtra("image_title");
        imageUrl = getIntent().getStringExtra("image_link");
        imageDetailsBinding.setImageLink(imageUrl);
        getSupportActionBar().setTitle(imageTitle);
        setImage();
        observeViewModel();
        imageDetailsViewModel.fetchCommentFromDB(imageId);
    }

    public void saveCommentInDB(View view) {
        if (!TextUtils.isEmpty(imageDetailsBinding.commentsEditText.getText().toString()))
            imageDetailsViewModel.saveCommentInDB(imageId, imageDetailsBinding.commentsEditText.getText().toString());
        else
            showToast("Please enter comments to submit.");
    }

    private void observeViewModel() {
        imageDetailsViewModel.isSaved().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSaved) {
                if (isSaved) {
                    showToast("Saved successfully.");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    imageDetailsBinding.commentsEditText.clearFocus();
                } else
                    showToast("Error while saving the comment.");
            }
        });
        imageDetailsViewModel.getComment().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                imageDetailsBinding.setImageComment(s);
            }
        });
    }

    private void showToast(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    public void setImage() {
        Glide.with(this)
                .load(imageUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        imageDetailsBinding.progressBar.setVisibility(View.GONE);
                        showToast("Error loading the image.");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        imageDetailsBinding.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(imageDetailsBinding.image);

    }

}
