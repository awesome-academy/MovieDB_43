package com.asterisk.tuandao.themoviedb.ui.detail

import android.os.Bundle
import android.util.Log
import com.asterisk.tuandao.themoviedb.BuildConfig
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment

class YouTubeVideoFragment : YouTubePlayerFragment(), YouTubePlayer.OnInitializedListener {

    private var youTubePlayer: YouTubePlayer? = null
    private var videoId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(BuildConfig.YOUTUBE_API_KEY, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        youTubePlayer?.release()
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, restored: Boolean) {
        youTubePlayer = player
        if (!restored && videoId != null) {
            youTubePlayer?.fullscreenControlFlags = YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
            youTubePlayer?.cueVideo(videoId)
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        result: YouTubeInitializationResult?
    ) {
        this.youTubePlayer = null
    }

    fun setVideoId(videoId: String?) {
        this.videoId = videoId
        youTubePlayer?.cueVideo(videoId)
    }

    fun playVideo() {
        youTubePlayer?.play()
    }

    fun pause() {
        youTubePlayer?.pause()
    }
}
