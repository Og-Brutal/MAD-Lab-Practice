package com.example.local_notifications

import android.app.AlertDialog
import android.media.MediaPlayer
import android.net.Uri
import android.widget.MediaController
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MediaActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_media)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val playAudio=findViewById<Button>(R.id.playAudioButton)
        val playVideo=findViewById<Button>(R.id.playVideoButton)
        playAudio.setOnClickListener {
            onStop()
            Toast.makeText(this,"Audio is loading !" ,Toast.LENGTH_SHORT).show()
            playAudio("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
        }
        playVideo.setOnClickListener {
            onStop()
            Toast.makeText(this,"Video is loading !" ,Toast.LENGTH_SHORT).show()
            playVideo("https://www.w3schools.com/html/mov_bbb.mp4")
        }
    }

    private fun playVideo(url: String) {
        val videoView= VideoView(this)
        val mediaController= MediaController(this)

        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse(url))

        val dialogue= AlertDialog.Builder(this)
            .setTitle("Video Player")
            .setView(videoView)
            .setNegativeButton("close") {d,_ ->
                videoView.stopPlayback()
                d.dismiss()
            }.create()

        dialogue.show()


        videoView.setOnPreparedListener { mp ->
            mp.start()
        }

        videoView.setOnErrorListener { _, _, _ ->
            Toast.makeText(this, "Error playing video", Toast.LENGTH_SHORT).show()
            true
        }



    }

    private fun playAudio(url:String) {
        mediaPlayer= MediaPlayer().apply {
            try {
                setDataSource(url)
                prepareAsync()
                setOnPreparedListener { mp ->
                    mp.start()
                    Toast.makeText(this@MediaActivity, "Audio playing!", Toast.LENGTH_SHORT).show()
                }
                setOnErrorListener { player, i, i1 ->
                    Toast.makeText(this@MediaActivity, "Error playing audio", Toast.LENGTH_SHORT)
                        .show()
                    true
                }
            }
            catch(e : Exception){
                e.printStackTrace()
                Toast.makeText(this@MediaActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun onStope(){
        mediaPlayer?.apply {
            if(isPlaying) stop()
            release()
        }
        mediaPlayer=null
    }

    override fun onStop() {
        super.onStop()
        onStope()
    }
}