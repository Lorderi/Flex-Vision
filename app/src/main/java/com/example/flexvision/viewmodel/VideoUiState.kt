package com.example.flexvision.viewmodel

import com.example.flexvision.model.Video

data class VideoUiState(
    val videos: List<Video> = emptyList(),
)
