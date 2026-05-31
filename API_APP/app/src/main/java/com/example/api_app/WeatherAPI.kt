package com.example.api_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.json.JSONObject


class WeatherAPI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weather_api)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val latitude: EditText=findViewById<EditText>(R.id.latitudeEditText)
        val longitude: EditText=findViewById<EditText>(R.id.longitudeEditText)
        val fetchWeather: Button=findViewById<Button>(R.id.fetchWeatherButton)
        val showWeather: TextView=findViewById<TextView>(R.id.weatherTextView)

        fetchWeather.setOnClickListener {
            val _latitude=latitude.text.toString()
            val _longitude=longitude.text.toString()

            val client= OkHttpClient()

            val request= Request.Builder()
                .url("https://api.open-meteo.com/v1/forecast?latitude=$_latitude&longitude=$_longitude&current_weather=true")
                .build()

            client.newCall(request).enqueue(object: Callback{

                override fun onFailure(call: Call,e: IOException){
                    runOnUiThread {
                        showWeather.text="There is no Internet !"
                    }

                }

                override fun onResponse(call: Call,response: Response){
                    if(response.isSuccessful){
                        val rawData=response.body?.string()
                        val jsonObject= JSONObject(rawData)

                        val current_weather=jsonObject.getJSONObject("current_weather")
                        val temp=current_weather.getDouble("temperature")
                        runOnUiThread {
                            showWeather.text=temp.toString()
                        }

                    }
                    else{
                        runOnUiThread {
                            showWeather.text="Error code is out of range of 200..291"
                        }

                    }
                }


            })




        }


    }
}


