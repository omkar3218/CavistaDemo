package com.omkar.cavistademo.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omkar.cavistademo.R
import com.omkar.cavistademo.data.model.Image
import com.omkar.cavistademo.databinding.ImageListItemBinding

class ImageListAdapter(
    private val articleDataModels: List<Image>?,
    private var context: SearchImageListFragment
) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ImageListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.image_list_item,
            null,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.userImageView.setImageDrawable(null)
        if (articleDataModels != null) {
            holder.binding.imageLink = articleDataModels[position].link
        }
        holder.binding.userImageView.setOnClickListener {
            articleDataModels?.get(position)
                ?.let { it1 -> context.navigateToImageDetailsScreen(it1) }
        }

    }

    override fun getItemCount(): Int {
        return articleDataModels?.size ?: 0
    }


    inner class ViewHolder internal constructor(val binding: ImageListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}