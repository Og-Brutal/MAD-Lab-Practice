package com.example.local_notifications

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.messaging.FirebaseMessaging
import kotlin.time.TestTimeSource

class FirebaseNotification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firebase_notification)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        println("Hey !")
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->

            if(!task.isSuccessful){
                return@addOnCompleteListener
            }

            Log.d("Token : ","${task.result}")
            findViewById<TextView>(R.id.tvToken).text=task.result.toString()


        }
    }
}