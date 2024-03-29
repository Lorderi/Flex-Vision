package com.example.flexvision.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flexvision.R
import com.example.flexvision.databinding.CardVideoBinding
import com.example.flexvision.model.Video

class VideoViewHolder(
    private val binding: CardVideoBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private fun updatePreview() {
        Glide.with(binding.preview)
            .load("")
            .placeholder(R.mipmap.ic_launcher_video_template)
            .into(binding.preview)
    }

    fun bind(video: Video) {
        binding.name.text = video.name
        binding.author.text = video.author
        binding.date.text = video.date
        binding.authorInitials.text = video.author.take(1)

        updatePreview()
    }
}
