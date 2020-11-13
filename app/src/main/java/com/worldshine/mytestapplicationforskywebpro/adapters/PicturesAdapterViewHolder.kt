package com.worldshine.mytestapplicationforskywebpro.adapters

import androidx.recyclerview.widget.RecyclerView
import com.worldshine.mytestapplicationforskywebpro.databinding.PicturesItemBinding
import com.worldshine.mytestapplicationforskywebpro.model.PicturesResponse

class PicturesAdapterViewHolder(private val binding: PicturesItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(picture: PicturesResponse) {
        binding.tvPictures.text = picture.author
    }
}