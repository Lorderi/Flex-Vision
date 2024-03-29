package com.example.flexvision.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import com.example.flexvision.App
import com.example.flexvision.Decoration.OffsetDecoration
import com.example.flexvision.R
import com.example.flexvision.adapter.VideosAdapter
import com.example.flexvision.databinding.FragmentVideosBinding
import com.example.flexvision.fragment.FragmentPlayer.Companion.ARG_PLAYER_ID
import com.example.flexvision.model.Video
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
                    VideoViewModel((requireContext().applicationContext as App).videoRepository)
                }
            }
        }

        val adapter = VideosAdapter(
            object : VideosAdapter.VideoListener {
                override fun onDeleteClicked(video: Video) {
                    viewModel.deleteById(video.id)
                }

                override fun onVideoClicked(video: Video) {
                    requireParentFragment()
                        .requireParentFragment()
                        .findNavController()
                        .navigate(
                            R.id.action_fragmentBottomMenu_to_fragmentPlayer,
                            bundleOf(
                                ARG_PLAYER_ID to video.id
                            )
                        )
                }
            }
        )

        binding.list.adapter = adapter

        binding.list.addItemDecoration(
            OffsetDecoration(resources.getDimensionPixelSize(R.dimen.small_spacing))
        )

        viewModel.uiState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                adapter.submitList(it.videos)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        return binding.root
    }
}
