package com.example.flexvision.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flexvision.databinding.FragmentNewVideoBinding
import com.example.flexvision.repository.InMemoryVideoRepository
import com.example.flexvision.viewmodel.NewVideoViewModel
import com.example.flexvision.viewmodel.ToolbarViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach

class FragmentNewVideo : Fragment() {

    companion object {
        const val ARG_VIDEO_ID = "ARG_VIDEO_ID"
    }

    private val toolbarViewModel by activityViewModels<ToolbarViewModel>()
    override fun onStart() {
        super.onStart()
        toolbarViewModel.setSaveVisibility(true)
    }

    override fun onStop() {
        super.onStop()
        toolbarViewModel.setSaveVisibility(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewVideoBinding.inflate(inflater, container, false)

        val id = arguments?.getLong(ARG_VIDEO_ID) ?: 0L

        val viewModel by viewModels<NewVideoViewModel> {
            viewModelFactory {
                NewVideoViewModel(
                    repository = InMemoryVideoRepository(),
                    videoId = id,
                )
            }
        }

        toolbarViewModel.saveClicked
            .filter { it }
            .onEach {
                // TODO
            }

        return binding.root
    }

}