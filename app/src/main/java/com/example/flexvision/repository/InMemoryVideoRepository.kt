package com.example.flexvision.repository

import com.example.flexvision.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryVideoRepository : VideoRepository {
    private val state = MutableStateFlow(
        List(20) {
            Video(
                id = it.toLong() + 1L,
                videoName = "№${it + 1L}  Yappie name",
                authorName = "Artemiy Bokov",
                date = "25.03.2024 17:00",
            )
        }
            .reversed()
    )

    private var nextId = state.value.first().id
    override fun getVideos(): Flow<List<Video>> = state.asStateFlow()
    override fun deleteById(id: Long) {
        state.update { videos ->
            videos.filter { it.id != id }
        }
    }

    override fun saveVideo(videoId: Long, name: String) {
        // TODO
    }
}
