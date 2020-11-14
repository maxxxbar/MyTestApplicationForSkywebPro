package com.worldshine.mytestapplicationforskywebpro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.worldshine.mytestapplicationforskywebpro.databinding.PicturesItemBinding
import com.worldshine.mytestapplicationforskywebpro.model.PicturesResponse

class PicturesAdapter : PagingDataAdapter<PicturesResponse, PicturesAdapterViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesAdapterViewHolder {
        val binding =
            PicturesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PicturesAdapterViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PicturesAdapterViewHolder, position: Int) {
        val picture = getItem(position)
        picture?.let {
            holder.bind(it)
        }
    }


    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<PicturesResponse>() {
            override fun areItemsTheSame(
                oldItem: PicturesResponse,
                newItem: PicturesResponse
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PicturesResponse,
                newItem: PicturesResponse
            ): Boolean = oldItem == newItem
        }
    }


}

