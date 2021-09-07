package ru.mintrocket.gen_motion_video.screens.video_split_horizontal

import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.mintrocket.gen_motion_video.R
import ru.mintrocket.gen_motion_video.databinding.FragmentVideoBinding
import ru.mintrocket.gen_motion_video.screens.BaseFragment
import ru.mintrocket.gen_motion_video.screens.video_generated.VideoGeneratedFragment
import ru.mintrocket.gen_motion_video.use_case.GetVideoSplitHorizontalUseCase

class VideoSplitHorizontalFragment(): BaseFragment(R.layout.fragment_video) {

    private lateinit var viewModel: VideoSplitHorizontalViewModel

    private val binding by viewBinding<FragmentVideoBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(VideoSplitHorizontalViewModel::class.java)
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
        viewModel.getUrlVideo(GetVideoSplitHorizontalUseCase())
    }

    companion object{
        val instance: VideoSplitHorizontalFragment by lazy {
            VideoSplitHorizontalFragment()
        }
    }
}