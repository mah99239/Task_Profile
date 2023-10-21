package com.mz.profile.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mz.profile.R
import com.mz.profile.databinding.FragmentPhotosBinding
import com.mz.profile.presentation.viewmodel.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment(), View.OnTouchListener {

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!
    val viewModel: PhotosViewModel by activityViewModels()
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private lateinit var gestureDetector: GestureDetector


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initViewModel()
        handleTouchEvent()

    }

    private fun handleTouchEvent() {
        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())
        gestureDetector = GestureDetector(requireContext(), GestureListener())

        binding.imvPhoto.setOnTouchListener(this)
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val scaleFactor = detector.scaleFactor
            val currentScale = binding.imvPhoto.scaleX

            val newScale = currentScale * scaleFactor
            binding.imvPhoto.scaleX = newScale
            binding.imvPhoto.scaleY = newScale

            return true
        }
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(event: MotionEvent): Boolean {
            // Handle double-tap gesture if needed
            return true
        }
    }

    private fun initViewModel() {
        binding.viewModel = viewModel

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_share, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_button -> {
                handleShareImage()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleShareImage() {
        viewModel.imageUrl.observe(viewLifecycleOwner) {
            val uri = Uri.parse(it)

            shareImage(uri)
        }
    }

    private fun shareImage(imageUri: Uri) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, imageUri)

        startActivity(Intent.createChooser(intent, "Share Image "))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}