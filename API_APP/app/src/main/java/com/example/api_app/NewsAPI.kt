package com.example.api_app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import okhttp3.Call
import okhttp3.Response
import org.json.JSONObject

class NewsAPI : AppCompatActivity() {
    // API key is loaded from assets at runtime to avoid committing secrets.
    private val API_KEY: String by lazy { loadApiKey() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_api)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fetchNews=findViewById<Button>(R.id.fetchNewsButton)
        val showsNews=findViewById<TextView>(R.id.newsTextView)

        fetchNews.setOnClickListener {

            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=$API_KEY")
                .build()

            client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        showsNews.text = "Error: ${e.message}"
                    }
                }

                override fun onResponse(call: Call, res: Response) {

                    if (res.isSuccessful) {

                        val rawData = res.body?.string()

                        try {

                            val jsonObject = JSONObject(rawData)
                            val articles = jsonObject.getJSONArray("articles")

                            val stringBuilder = StringBuilder()

                            for (i in 0 until articles.length()) {

                                val article = articles.getJSONObject(i)

                                val title = article.getString("title")
                                val description = article.getString("description")

                                stringBuilder.append("📰 ")
                                stringBuilder.append(title)
                                stringBuilder.append("\n")

                                if (description != "null") {
                                    stringBuilder.append(description)
                                } else {
                                    stringBuilder.append("No description")
                                }

                                stringBuilder.append("\n\n----------------------\n\n")
                            }

                            runOnUiThread {
                                showsNews.text = stringBuilder.toString()
                            }

                        } catch (e: Exception) {
                            runOnUiThread {
                                showsNews.text = "Parsing error: ${e.message}"
                            }
                        }

                    } else {
                        runOnUiThread {
                            showsNews.text = "Response Error: ${res.code}"
                        }
                    }
                }

            })
        }

    private fun loadApiKey(): String {
        // Loads `API_KEY` from `src/main/assets/apikey.properties`.
        // Keep the real file out of version control and use the example file.
        return try {
            assets.open("apikey.properties").use { input ->
                val props = java.util.Properties()
                props.load(input)
                props.getProperty("API_KEY") ?: ""
            }
        } catch (e: Exception) {
            ""
        }
    }

    }
}