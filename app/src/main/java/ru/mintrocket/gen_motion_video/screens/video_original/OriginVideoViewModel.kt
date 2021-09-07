package ru.mintrocket.gen_motion_video.screens.video_original

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mintrocket.gen_motion_video.net.model.RetrofitClient.retrofitService
import ru.mintrocket.gen_motion_video.net.model.VideosUrls

class OriginVideoViewModel : ViewModel() {

    var urlOriginVideo = MutableLiveData<String>()
    var errorMsg = MutableLiveData<String>()

    fun getUrlVideo() {
        if (urlOriginVideo.value != null) return

        // todo: переместить в videoModel main как lazy
        retrofitService.getUrls().enqueue(object : Callback<VideosUrls>{
            override fun onResponse(call: Call<VideosUrls>, response: Response<VideosUrls>) {
                val videosUrls = response.body()
                urlOriginVideo.postValue(videosUrls?.src)
            }

            override fun onFailure(call: Call<VideosUrls>, t: Throwable) {
                errorMsg.postValue(t.localizedMessage)
            }
        })

    }
}