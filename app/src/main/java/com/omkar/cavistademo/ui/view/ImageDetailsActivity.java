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
    private ActivityImageDetailsBinding imageDetailsBinding;

    @Inject
    ViewModelFactory viewModelFactory;

    private ImageDetailsViewModel imageDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        imageDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        imageDetailsViewModel = viewModelFactory.create(ImageDetailsViewModel.class);
        imageId = getIntent().getStringExtra(getString(R.string.key_image_id));
        String imageTitle = getIntent().getStringExtra(getString(R.string.key_image_title));
        getSupportActionBar().setTitle(imageTitle != null ? imageTitle : "");
        setImage();
        observeViewModel();
        imageDetailsViewModel.fetchCommentFromDB(imageId);
    }

    public void saveCommentInDB(View view) {
        if (!TextUtils.isEmpty(Objects.requireNonNull(imageDetailsBinding.commentsEditText.getText()).toString()))
            imageDetailsViewModel.saveCommentInDB(imageId, imageDetailsBinding.commentsEditText.getText().toString());
        else
            showToast(getString(R.string.enter_comment_msg));
    }

    private void observeViewModel() {
        imageDetailsViewModel.isSaved().observe(this, isSaved -> {
            if (isSaved) {
                showToast(getString(R.string.save_success_msg));
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                imageDetailsBinding.commentsEditText.clearFocus();
            } else
                showToast(getString(R.string.save_error_msg));
        });
        imageDetailsViewModel.getComment().observe(this, s -> imageDetailsBinding.setImageComment(s));
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

    private void setImage() {
        String imageUrl = getIntent().getStringExtra(getString(R.string.key_image_link));
        if (imageUrl != null) {
            Glide.with(this)
                    .load(imageUrl)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            imageDetailsBinding.progressBar.setVisibility(View.GONE);
                            showToast(getString(R.string.load_error_msg));
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

}
