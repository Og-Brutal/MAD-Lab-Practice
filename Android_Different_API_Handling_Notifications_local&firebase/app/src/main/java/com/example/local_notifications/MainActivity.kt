package com.example.local_notifications

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val localNotificationBtn=findViewById<Button>(R.id.localNotificationButton)
        val firebaseNotification=findViewById<Button>(R.id.FirebaseNotification)
        val cameraAPI=findViewById<Button>(R.id.camerAPI)
        val mediaActivity=findViewById<Button>(R.id.mediaActivity)
        val mapsActivity=findViewById<Button>(R.id.mapsActivity)


        localNotificationBtn.setOnClickListener {
            val intent= Intent(this,local_notification::class.java)
            startActivity(intent)
        }

        firebaseNotification.setOnClickListener {
            val intent= Intent(this, FirebaseNotification::class.java)
            startActivity(intent)
        }
        cameraAPI.setOnClickListener {
            val intent=Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        mediaActivity.setOnClickListener {
            val intent=Intent(this, MediaActivity::class.java)
            startActivity(intent)
        }


        mapsActivity.setOnClickListener {
            val intent=Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }
}