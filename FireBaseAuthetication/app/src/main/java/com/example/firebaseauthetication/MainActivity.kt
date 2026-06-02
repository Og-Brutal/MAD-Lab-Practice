package com.example.firebaseauthetication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

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

        val auth=FirebaseAuth.getInstance()

        val signup=findViewById<TextView>(R.id.goToSignUp)
        val loginBtn=findViewById<Button>(R.id.loginBtn)


        signup.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


        loginBtn.setOnClickListener {

            val loginemail=findViewById<EditText>(R.id.loginEmail).text.toString()
            val loginpass=findViewById<EditText>(R.id.loginPassword).text.toString()

            auth.signInWithEmailAndPassword(loginemail,loginpass)
                .addOnCompleteListener { task ->

                    if(task.isSuccessful) {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)

                        finish()
                    }
                    else{
                        Toast.makeText(this,"Invalid credentials !" ,Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}