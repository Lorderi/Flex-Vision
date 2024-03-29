package com.example.flexvision.viewmodel

import androidx.lifecycle.ViewModel
import com.example.flexvision.repository.VideoRepository

class PlayerViewModel(
    private val repository: VideoRepository,
    private val videoId: Long = 0,
) : ViewModel() {
    fun getVideo() = repository.getById(videoId)

    fun deleteById(id: Long) {
        repository.deleteById(id)
    }
}
