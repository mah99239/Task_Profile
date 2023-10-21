package com.mz.profile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.casecode.pos.base.BaseAdapter
import com.casecode.pos.base.BaseViewHolder
import com.mz.profile.R
import com.mz.profile.databinding.ItemAlbumBinding
import com.mz.profile.domain.model.Album
import timber.log.Timber

class AlbumsAdapter(val itemClick: (Album) -> Unit): BaseAdapter<Album>(AlbumsDiffCallback) {

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Album]
     * has been updated.
     */
    companion object AlbumsDiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {

            return oldItem === newItem
        }


        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }


    }
    inner class AlbumViewHolder(binding: ItemAlbumBinding) :
        BaseViewHolder<ItemAlbumBinding, Album>(binding) {
        init {
            binding.apply {
                itemView.setOnClickListener {

                    album?.run {
                        itemClick(this)
                    }
                }
            }
        }

        override fun bind(element: Album) {
            super.bind(element)
            Timber.e("element = $element")
            binding.album = element
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<out ViewDataBinding, Album> {
        return AlbumViewHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getItemViewType(position: Int): Int {
        return R.layout.item_album
    }

  /*  *//**
     *  parent list is immutable and override here to use mutableList.
     *//*
    override fun submitList(list: MutableList<Album>?) {
        super.submitList(
            list?.let { ArrayList(it) })
    }*/

}