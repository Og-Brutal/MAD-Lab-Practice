package com.example.dbdemo

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dbdemo.Fragments.AddContactFragment
import com.example.dbdemo.Fragments.ContactListFragment

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
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnList = findViewById<Button>(R.id.btnList)

        if(savedInstanceState==null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddContactFragment())
                .addToBackStack(null)
                .commit()
        }

        btnAdd.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddContactFragment())
                .addToBackStack(null)
                .commit()
        }

        btnList.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ContactListFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}