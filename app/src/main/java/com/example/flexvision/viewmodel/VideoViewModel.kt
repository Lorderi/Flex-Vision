package com.example.flexvision.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flexvision.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class VideoViewModel(private val repository: VideoRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(VideoUiState())
    val uiState: StateFlow<VideoUiState> = _uiState.asStateFlow()

    init {
        repository.getVideos()
            .onEach { videos ->
                _uiState.update {
                    it.copy(videos = videos)
                }
            }
            .launchIn(viewModelScope)
    }

    fun deleteById(id: Long) {
        repository.deleteById(id)
    }

    //  TODO
    //    fun getById(id: Long) {
    //        repository.getById(id)
    //    }
}