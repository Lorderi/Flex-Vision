package com.example.flexvision.repository

import com.example.flexvision.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryVideoRepository : VideoRepository {
    private val state = MutableStateFlow(
        List(10) {
            Video(
                id = it.toLong() + 1L,
                name = "№${it + 1L}  Yappie name",
                author = "Artemiy Bokov",
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

    override fun saveVideo(videoId: Long, name: String, description: String, videoUrl: String) {
        state.update { videos ->
            buildList(capacity = videos.size + 1) {
                add(
                    Video(
                        id = ++nextId,
                        name = name,
                        description = description,
                        uploadUrl = videoUrl
                    )
                )
                addAll(videos)
            }
        }
    }

    override fun getById(id: Long) = state.value.filter { it.id == id }[0]
}