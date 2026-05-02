package com.example.learning_fragments

import android.app.Fragment
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learning_fragments.Fragments.firstFragment
import com.example.learning_fragments.Fragments.secondFragment

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

        val button1: Button=findViewById<Button>(R.id.btn1)

        val button2:Button=findViewById<Button>(R.id.btn2)

        val linearLayout=findViewById<LinearLayout>(R.id.placeHolder)



        button1.setOnClickListener {

            val firstFragment= firstFragment("wahb",12)

            val fragmentTransaction=supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.placeHolder,firstFragment)

            fragmentTransaction.commit()
        }
        button2.setOnClickListener {
            val secondFragment= secondFragment()
            val fragmentTransaction=supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.placeHolder,secondFragment)
            fragmentTransaction.commit()
        }


    }


}