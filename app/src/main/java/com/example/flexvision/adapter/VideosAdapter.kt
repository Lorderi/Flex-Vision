package com.example.flexvision.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flexvision.R
import com.example.flexvision.databinding.CardVideoBinding
import com.example.flexvision.model.Video


class VideosAdapter(
    private val listener: VideoListener,
) : ListAdapter<Video, VideoViewHolder>(VideoDiffCallback()) {
    interface VideoListener {
        fun onDeleteClicked(video: Video)
        fun onVideoClicked(video: Video)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardVideoBinding.inflate(layoutInflater, parent, false)

        val viewHolder = VideoViewHolder(binding)

        binding.menu.setOnClickListener {
            PopupMenu(it.context, it).apply {
                inflate(R.menu.video_menu)

                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.delete -> {
                            listener.onDeleteClicked(getItem(viewHolder.adapterPosition))
                            true
                        }

                        else -> false
                    }
                }
                show()
            }
        }

        binding.root.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onVideoClicked(getItem(position))
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
