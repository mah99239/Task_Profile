package com.mz.profile.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.mz.profile.databinding.FragmentAlbumPhotosBinding
import com.mz.profile.presentation.adapter.PhotoGridAdapter
import com.mz.profile.presentation.base.BaseTextWatcher
import com.mz.profile.presentation.viewmodel.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AlbumPhotosFragment : Fragment() {

    private var _binding: FragmentAlbumPhotosBinding? = null
    private val binding get() = _binding!!
    val viewModel: PhotosViewModel by activityViewModels()
    private val args: AlbumPhotosFragmentArgs by navArgs()


    private val photosAdapter: PhotoGridAdapter by lazy {
        PhotoGridAdapter {
            viewModel.setImageSelected(it)
            val action = AlbumPhotosFragmentDirections.actionNavAlbumPhotoToNavPhotosFragment()
            requireView().findNavController().navigate(action)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumPhotosBinding.inflate(inflater, container, false)
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
        handleSearchInput()
    }

    private fun initViewModel() {

        val id = args.albumId
        viewModel.getAlbumId(id)
    }

    private fun initAdapter() {
        binding.rvAlbumPhotos.adapter = photosAdapter

    }

    private fun observerViewModel() {
        viewModel.photos.observe(viewLifecycleOwner) {
            Timber.e("obseverViewModel: $it")
            photosAdapter.submitList(it?.toMutableList())
        }

    }

    private fun handleSearchInput() {
        binding.etPhotosSearch.addTextChangedListener(object : BaseTextWatcher() {

            override fun onTextChanged(
                s: CharSequence?,
                startIndex: Int,
                beforeLength: Int,
                afterLength: Int
            ) {
                val searchTitle = s.toString()

                // Get the image title for the search title from the view model.
                viewModel.getImageTitle(searchTitle)
            }


        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}