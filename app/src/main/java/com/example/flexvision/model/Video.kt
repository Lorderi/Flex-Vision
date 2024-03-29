package com.example.flexvision.model

data class Video(
    val id: Long = 0L,
    val name: String = "",
    val author: String = "",
    val date: String = "",
    val description: String = "",
    val uploadUrl: String = "",
    val previewUrl: String = "",
    val authorId: Long = 0L,
)
