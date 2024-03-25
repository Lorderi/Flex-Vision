package com.example.flexvision.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flexvision.adapter.VideosAdapter
import com.example.flexvision.databinding.FragmentVideosBinding
import com.example.flexvision.model.Video
import com.example.flexvision.repository.InMemoryVideoRepository
import com.example.flexvision.viewmodel.VideoViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class FragmentVideos : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVideosBinding.inflate(inflater, container, false)

        val viewModel by viewModels<VideoViewModel> {
            viewModelFactory {
                initializer {
                    VideoViewModel(InMemoryVideoRepository())
                }
            }
        }

        val adapter = VideosAdapter(
            object : VideosAdapter.VideoListener {
                override fun onDeleteClicked(video: Video) {
                    viewModel.deleteById(video.id)
                }
            }
        )

        binding.list.adapter = adapter

        // TODO Decorator

        viewModel.uiState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                adapter.submitList(it.videos)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        return binding.root
    }
}