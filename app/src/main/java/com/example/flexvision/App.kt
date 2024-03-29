package com.example.flexvision

import android.app.Application
import com.example.flexvision.repository.InMemoryVideoRepository

class App : Application() {
    val videoRepository: InMemoryVideoRepository by lazy { InMemoryVideoRepository() }
}
