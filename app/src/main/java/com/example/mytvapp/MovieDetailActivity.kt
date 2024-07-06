package com.example.mytvapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


import android.net.Uri
import android.widget.Button
import android.widget.TextView

import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieTitle = intent.getStringExtra("MOVIE_TITLE") ?: ""
        val movieUrl = intent.getStringExtra("MOVIE_URL") ?: ""

        val titleTextView: TextView = findViewById(R.id.movie_title)
        val watchNowButton: Button = findViewById(R.id.watch_now_button)

        titleTextView.text = movieTitle

        playerView = findViewById(R.id.player_view)

        exoPlayer = ExoPlayer.Builder(this).build()
        playerView.player = exoPlayer

        val mediaItem = MediaItem.fromUri(Uri.parse(movieUrl))

        watchNowButton.setOnClickListener {
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}
