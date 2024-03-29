package com.example.flexvision.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import com.example.flexvision.App
import com.example.flexvision.R
import com.example.flexvision.databinding.FragmentPlayerBinding
import com.example.flexvision.model.Video
import com.example.flexvision.viewmodel.PlayerViewModel

class FragmentPlayer : Fragment() {
    companion object {
        const val ARG_PLAYER_ID = "ARG_PLAYER_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPlayerBinding.inflate(layoutInflater)

        val id = arguments?.getLong(ARG_PLAYER_ID) ?: 0L

        val viewModel by viewModels<PlayerViewModel> {
            viewModelFactory {
                initializer {
                    PlayerViewModel(
                        repository = (requireContext().applicationContext as App).videoRepository,
                        videoId = id,
                    )
                }
            }
        }

        val video = viewModel.getVideo()

        bind(binding, video)

        binding.menu.setOnClickListener {
            PopupMenu(it.context, it).apply {
                inflate(R.menu.video_menu)

                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.delete -> {
                            viewModel.deleteById(video.id)
                            findNavController().navigateUp()
                            true
                        }

                        else -> false
                    }
                }
                show()
            }
        }

        return binding.root
    }

    private fun bind(binding: FragmentPlayerBinding, video: Video) {
        binding.name.text = video.name
        binding.author.text = video.author
        binding.date.text = video.date
        binding.authorInitials.text = video.author.take(1)
    }

}
