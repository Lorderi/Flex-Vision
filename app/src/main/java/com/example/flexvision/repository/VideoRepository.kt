package com.example.flexvision.repository

import com.example.flexvision.model.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun getVideos(): Flow<List<Video>>
    fun deleteById(id: Long)
    fun saveVideo(videoId: Long, name: String)
}
