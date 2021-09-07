package ru.mintrocket.gen_motion_video.use_case

import ru.mintrocket.gen_motion_video.common.ResultGetUrl

interface GetVideoUrl {
    fun execute(resultGetUrl: ResultGetUrl)
}