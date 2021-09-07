package ru.mintrocket.gen_motion_video.common

import android.util.Patterns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

fun pingURL(url: String, timeout: Int): Boolean {
    return try {
        val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
        connection.connectTimeout = timeout
        connection.readTimeout = timeout
        connection.requestMethod = "HEAD"
        val responseCode: Int = connection.responseCode
        responseCode in 200..399
    } catch (exception: IOException) {
        false
    }
}

fun checkUrl(resultGetUrl: ResultGetUrl, url: String) {
    CoroutineScope(Dispatchers.IO).launch {
        if(Patterns.WEB_URL.matcher(url).matches()
            && pingURL(url, 2000)
        ) {
            CoroutineScope(Dispatchers.Main).launch{
                resultGetUrl.resultGetUrl(url)
            }
        }else{
            CoroutineScope(Dispatchers.Main).launch{
                resultGetUrl.error("URL ERROR!")
            }
        }
    }
}