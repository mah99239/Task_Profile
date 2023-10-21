package com.mz.profile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.casecode.pos.base.BaseAdapter
import com.casecode.pos.base.BaseViewHolder
import com.mz.profile.R
import com.mz.profile.databinding.ItemGridPhotoBinding
import com.mz.profile.domain.model.Album
import com.mz.profile.domain.model.Photo
import timber.log.Timber

class PhotoGridAdapter( val itemClick: (Photo) -> Unit ): BaseAdapter<Photo>(PhotoDiffCallback) {


    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Album]
     * has been updated.
     */
    companion object PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {

            return oldItem === newItem
        }


        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }


    }
    inner class PhotoViewHolder(binding: ItemGridPhotoBinding) :
        BaseViewHolder<ItemGridPhotoBinding, Photo>(binding) {
        init {
            binding.apply {
                itemView.setOnClickListener {

                    photo?.run {
                        itemClick(this)
                    }
                }
            }
        }

        override fun bind(element: Photo) {
            super.bind(element)
            Timber.e("element = $element")
            binding.photo = element
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<out ViewDataBinding, Photo> {
        return PhotoViewHolder(
            ItemGridPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun getItemViewType(position: Int): Int {
        return R.layout.item_album
    }

    /*
     *  parent list is immutable and override here to use mutableList.
     */
    override fun submitList(list: MutableList<Photo>?) {
        super.submitList(
            list?.let { ArrayList(it) })
    }

}