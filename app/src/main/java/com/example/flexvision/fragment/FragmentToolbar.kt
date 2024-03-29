package com.example.flexvision.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.flexvision.R
import com.example.flexvision.databinding.FragmentToolbarBinding
import com.example.flexvision.viewmodel.ToolbarViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FragmentToolbar : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentFragmentManager.beginTransaction()
            .setPrimaryNavigationFragment(this)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentToolbarBinding.inflate(inflater, container, false)

        val navController =
            requireNotNull(childFragmentManager.findFragmentById(R.id.container)).findNavController()

        binding.toolbar.setupWithNavController(navController)

        val saveItem = binding.toolbar.menu.findItem(R.id.save)

        val toolbarViewModel by activityViewModels<ToolbarViewModel>()

        toolbarViewModel.showSave
            .onEach {
                saveItem.isVisible = it
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        saveItem.setOnMenuItemClickListener {
            toolbarViewModel.saveClicked(true)
            true
        }

        return binding.root
    }

}
