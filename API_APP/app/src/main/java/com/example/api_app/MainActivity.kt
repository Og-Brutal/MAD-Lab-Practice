package com.example.api_app

import android.content.Intent
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



        val weatherAPIButton: Button = findViewById<Button>(R.id.btnWeatherApi)
        val newsAPIButton: Button = findViewById<Button>(R.id.btnNewsApi)
        val jsonPlaceHolder: Button=findViewById<Button>(R.id.btnJsonPlaceHolder)

        weatherAPIButton.setOnClickListener {
            val weatherINTENT= Intent(this, WeatherAPI::class.java)
            startActivity(weatherINTENT)
        }

        newsAPIButton.setOnClickListener {
            val newsINTENT=Intent(this, NewsAPI::class.java)
            startActivity(newsINTENT)
        }

        jsonPlaceHolder.setOnClickListener {
            val jsonINTENT=Intent(this,JSONPlaceHolder::class.java)
            startActivity(jsonINTENT)
        }
    }
}