package com.example.flexvision.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.flexvision.databinding.CardVideoBinding
import com.example.flexvision.model.Video

class VideoViewHolder(
    private val binding: CardVideoBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(video: Video) {
        binding.name.text = video.videoName
        binding.author.text = video.authorName
        binding.date.text = video.date
        binding.authorInitials.text = video.authorName.take(1)
    }
}
