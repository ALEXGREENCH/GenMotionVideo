package ru.mintrocket.gen_motion_video.net

import retrofit2.Call
import retrofit2.http.GET
import ru.mintrocket.gen_motion_video.net.model.VideosUrls

interface RetrofitServices {
    @GET("test/item")
    fun getUrls(): Call<VideosUrls>
}