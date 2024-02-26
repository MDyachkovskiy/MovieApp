package com.test.application.movie_details.utils

import androidx.lifecycle.Lifecycle
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class TrailerPlayerManager(
    private val lifecycle: Lifecycle,
    private var youTubePlayerView: YouTubePlayerView?,
) {

    private var currentYouTubePlayer: YouTubePlayer? = null

    init {
        youTubePlayerView?.let { lifecycle.addObserver(it) }
    }

    fun playTrailer(videoUrl: String, onStart: () -> Unit) {
        val videoId = extractVideoIdFromUrl(videoUrl)
        youTubePlayerView?.getYouTubePlayerWhenReady(object: YouTubePlayerCallback{
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                currentYouTubePlayer = youTubePlayer
                youTubePlayer.loadVideo(videoId, 0f)
                onStart()
            }
        })
    }

    fun cleanup() {
        currentYouTubePlayer?.pause()
        currentYouTubePlayer = null
        youTubePlayerView?.release()
        youTubePlayerView = null
    }
}