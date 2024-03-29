package com.example.flexvision.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import com.example.flexvision.App
import com.example.flexvision.databinding.FragmentNewVideoBinding
import com.example.flexvision.viewmodel.NewVideoViewModel
import com.example.flexvision.viewmodel.ToolbarViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
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
        val binding = FragmentNewVideoBinding.inflate(layoutInflater)

        val id = arguments?.getLong(ARG_VIDEO_ID) ?: 0L

        val viewModel by viewModels<NewVideoViewModel> {
            viewModelFactory {
                initializer {
                    NewVideoViewModel(
                        repository = (requireContext().applicationContext as App).videoRepository,
                        videoId = id,
                    )
                }
            }
        }

        toolbarViewModel.saveClicked
            .filter { it }
            .onEach {
                val name = binding.editName.text?.toString().orEmpty()
                val description = binding.editDescription.text?.toString().orEmpty()
                val videoUrl = binding.upload.text?.toString().orEmpty()

                if (name.isBlank() || description.isBlank() || videoUrl.isBlank()) {
                    Toast.makeText(requireContext(), "Text cannot be blank", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.saveVideo(name, description, videoUrl)
                    findNavController().navigateUp()
                }

                toolbarViewModel.saveClicked(false)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        return binding.root
    }
}
