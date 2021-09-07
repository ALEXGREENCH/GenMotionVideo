package ru.mintrocket.gen_motion_video.screens.video_original

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mintrocket.gen_motion_video.common.ResultGetUrl
import ru.mintrocket.gen_motion_video.use_case.GetVideoUrl

class VideoOriginViewModel : ViewModel() {

    var urlOriginVideo = MutableLiveData<String>()
    var errorMsg = MutableLiveData<String>()

    fun getUrlVideo(getVideoUrl: GetVideoUrl) {
        getVideoUrl.execute(object : ResultGetUrl{
            override fun resultGetUrl(url: String) {
                urlOriginVideo.postValue(url)
            }

            override fun error(error: String) {
                errorMsg.postValue(error)
            }
        })
    }
}