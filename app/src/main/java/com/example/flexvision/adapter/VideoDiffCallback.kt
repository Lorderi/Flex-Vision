package com.example.flexvision.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.flexvision.model.Video

class VideoDiffCallback : DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean = oldItem == newItem
}
