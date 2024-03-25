package com.example.flexvision.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.flexvision.R
import com.example.flexvision.databinding.FragmentBottomMenuBinding

class FragmentBottomMenu : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBottomMenuBinding.inflate(inflater, container, false)

//        val videoClickListener = View.OnClickListener {
//            findNavController()
//                .navigate(R.id.action_fragmentBottomMenu_to_fragmentNewVideo)
//        }

        val navController =
            requireNotNull(childFragmentManager.findFragmentById(R.id.container)).findNavController()

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.fragmentVideos -> {
//                    binding.addVideo.setOnClickListener(videoClickListener)
//                    binding.addVideo.animate()
//                        .scaleX(1F)
//                        .scaleY(1F)
//                }
//            }
//        }

        binding.bottomMenu.setupWithNavController(navController)

        return binding.root
    }
}
