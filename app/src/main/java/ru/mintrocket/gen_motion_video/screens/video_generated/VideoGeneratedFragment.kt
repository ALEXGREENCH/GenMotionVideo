package ru.mintrocket.gen_motion_video.screens.video_generated

import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.mintrocket.gen_motion_video.R
import ru.mintrocket.gen_motion_video.databinding.FragmentVideoBinding
import ru.mintrocket.gen_motion_video.screens.BaseFragment
import ru.mintrocket.gen_motion_video.screens.video_original.VideoOriginViewModel
import ru.mintrocket.gen_motion_video.use_case.GetVideoGenUrlUseCase

class VideoGeneratedFragment : BaseFragment(R.layout.fragment_video) {

    private lateinit var viewModel: VideoGeneratedViewModel

    private val binding by viewBinding<FragmentVideoBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(VideoGeneratedViewModel::class.java)
        viewModel.urlOriginVideo.observe(viewLifecycleOwner, {
            binding.videoView.setVideoPath(it)
            binding.videoView.requestFocus()
            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(binding.videoView)
            binding.videoView.setMediaController(mediaController)
            binding.videoView.start()

        })
        viewModel.errorMsg.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.getUrlVideo(GetVideoGenUrlUseCase())
    }

    companion object{
        val instance: VideoGeneratedFragment by lazy {
            VideoGeneratedFragment()
        }
    }
}