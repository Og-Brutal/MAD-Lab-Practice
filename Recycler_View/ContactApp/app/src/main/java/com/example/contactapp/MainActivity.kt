package com.example.contactapp

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.Models.Contact

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

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)

        val contactList = mutableListOf(
            Contact("Ali", "03001234567",),
            Contact("Sara", "03111234567", ),
            Contact("Ahmed", "03221234567",),
            Contact("Zain", "03331234567",),
            Contact("Zain", "03331234567",),
            Contact("Zain", "03331234567",),
            Contact("Zain", "03331234567",),
            Contact("Zain", "03331234567",),
            Contact("Zain", "03331234567",),
            Contact("Ali", "03001234567",),
            Contact("Ali", "03001234567",),
            Contact("Ali", "03001234567",)
        )
        val adapter= RecyclerViewAdapter(contactList)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter=adapter

    }
}