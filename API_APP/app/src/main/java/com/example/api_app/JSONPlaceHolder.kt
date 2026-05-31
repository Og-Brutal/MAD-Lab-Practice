package com.example.api_app

import android.app.DownloadManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class JSONPlaceHolder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jsonplace_holder)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val displayBox: TextView=findViewById<TextView>(R.id.jsonPlaceholderTextView)
        val postBtn:Button=findViewById<Button>(R.id.sendPostButton)
        val putBtn:Button=findViewById<Button>(R.id.sendPutButton)
        val patchBtn:Button=findViewById<Button>(R.id.sendPatchButton)
        val deleteBtn:Button=findViewById<Button>(R.id.sendDeleteButton)

        val client= OkHttpClient()
        val jsonMediaType = "application/json; charset=utf-8".toMediaType()
        val request= Request.Builder()
            .url("https://dummyjson.com/posts")
            .build()

        client.newCall(request).enqueue(object: Callback{

            override fun onFailure(call: Call, e: IOException){}

            override fun onResponse(call: Call,res: Response){
                if(res.isSuccessful){
                    val jsonObject= JSONObject(res.body?.string())
                    val posts=jsonObject.getJSONArray("posts")
                    val stringbuilder=StringBuilder()
                    for(i in 0 until posts.length()){
                        val post=posts.getJSONObject(i)
                        val id=post.optInt("id",-1)
                        val title=post.optString("title","No Title")
                        val body=post.optString("body","No Body")
                        stringbuilder.append("$id ) Title: $title \n")
                        stringbuilder.append("$body \n")
                        stringbuilder.append("\n\n --------------------------- \n\n")

                    }
                    runOnUiThread {
                        displayBox.text=stringbuilder
                    }
                }
                else{

                }
            }

        })


        postBtn.setOnClickListener {
            val newPOST= JSONObject().apply {
                put("title","daracola")
                put("body","akjsckas ckasjcnakjcnaks ckjanckjsa ckjasncasjk ckasjncaksjncask caskjcnaskc askjnc .")
                put("tags", JSONArray().apply{
                    put("tech")
                    put("india")
                })
                put("userId","31")
            }.toString().toRequestBody(jsonMediaType)


            val request=Request.Builder()
                .url("https://dummyjson.com/posts/add")
                .post(newPOST)
                .build()


            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException){}

                override fun onResponse(call: Call,res: Response){
                    if(res.isSuccessful){
                        val jsonObject= JSONObject(res.body?.string())
                        val id=jsonObject.optInt("id",-1)
                        val title=jsonObject.optString("title","N/A")

                        runOnUiThread {
                            Toast.makeText(
                                this@JSONPlaceHolder,
                                "Post created with ID: $id and Title: $title",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    else{

                    }
                }
            })

        }


    }
}