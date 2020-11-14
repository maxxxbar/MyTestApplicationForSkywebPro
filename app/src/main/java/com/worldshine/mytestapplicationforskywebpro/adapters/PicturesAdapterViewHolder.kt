package com.worldshine.mytestapplicationforskywebpro.adapters

import androidx.recyclerview.widget.RecyclerView
import com.worldshine.mytestapplicationforskywebpro.databinding.PicturesItemBinding
import com.worldshine.mytestapplicationforskywebpro.model.PicturesResponse
import com.worldshine.mytestapplicationforskywebpro.utils.loadImageWithGlide

class PicturesAdapterViewHolder(private val binding: PicturesItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(picture: PicturesResponse) {
        binding.ivPictures.loadImageWithGlide(picture.download_url)
        binding.tvPictures.text = picture.author
    }
}