package ru.mintrocket.gen_motion_video.net.model

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("test/item")
    fun getUrls(): Call<VideosUrls>
}