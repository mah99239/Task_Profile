package com.mz.profile.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.mz.profile.databinding.FragmentProfileBinding
import com.mz.profile.presentation.adapter.AlbumsAdapter
import com.mz.profile.presentation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private val albumsAdapter: AlbumsAdapter by lazy {
        AlbumsAdapter {
            val action =
                ProfileFragmentDirections.actionNavProfileFragmentToNavAlbumPhotosFragment(it.id)
            requireView().findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initViewModel()
        initAdapter()

        observerViewModel()
    }


    private fun initViewModel() {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    private fun initAdapter() {
        binding.rvProfile.adapter = albumsAdapter
    }

    private fun observerViewModel() {
        viewModel.albums.observe(viewLifecycleOwner) { albums ->
            Timber.e("observerViewModel:albums= $albums")
            albumsAdapter.submitList(albums)
        }
    }


    private fun messageWithSnack(message: String) {
        Snackbar.make(
            binding.root, message,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .setAnchorView(binding.root.bottom).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}