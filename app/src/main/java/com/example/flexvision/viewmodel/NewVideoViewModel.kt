package com.example.flexvision.viewmodel

import androidx.lifecycle.ViewModel
import com.example.flexvision.repository.VideoRepository

class NewVideoViewModel(
    private val repository: VideoRepository,
    private val videoId: Long = 0,
) : ViewModel() {
    fun saveVideo(name: String) {
        repository.saveVideo(videoId, name)
    }
}