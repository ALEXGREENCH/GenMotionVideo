package ru.mintrocket.gen_motion_video.use_case

import android.util.Patterns
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mintrocket.gen_motion_video.common.ResultGetUrl
import ru.mintrocket.gen_motion_video.net.RetrofitClient
import ru.mintrocket.gen_motion_video.net.model.VideosUrls

class GetVideoSplitHorizontalUseCase: GetVideoUrl {

    override fun execute(resultGetUrl: ResultGetUrl) {
        RetrofitClient.retrofitService.getUrls().enqueue(object : Callback<VideosUrls> {
            override fun onResponse(call: Call<VideosUrls>, response: Response<VideosUrls>) {
                val videosUrls = response.body()
                val url = videosUrls?.results?.split_h!!
                if(Patterns.WEB_URL.matcher(url).matches()) {
                    resultGetUrl.resultGetUrl(url)
                }else{
                    resultGetUrl.error("URL ERROR!")
                }
            }

            override fun onFailure(call: Call<VideosUrls>, t: Throwable) {
                resultGetUrl.error(t.localizedMessage)
            }
        })
    }

}