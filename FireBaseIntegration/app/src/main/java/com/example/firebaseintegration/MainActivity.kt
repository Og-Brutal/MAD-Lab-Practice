package com.example.firebaseintegration

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FirebaseFirestore.getInstance()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val etId = findViewById<EditText>(R.id.etId)
        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etCity = findViewById<EditText>(R.id.etCity)

        findViewById<Button>(R.id.btnCreate).setOnClickListener {
            val user = hashMapOf(
                "name" to etName.text.toString(),
                "age" to etAge.text.toString().toInt(),
                "city" to etCity.text.toString()
            )

            db.collection("users")
                .document(etId.text.toString())
                .set(user)
                .addOnSuccessListener {
                    loadUsers()
                }
        }

        findViewById<Button>(R.id.btnRead).setOnClickListener {
            loadUsers()
        }

        findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            val updates = hashMapOf<String, Any>(
                "name" to etName.text.toString(),
                "age" to etAge.text.toString().toInt(),
                "city" to etCity.text.toString()
            )

            db.collection("users")
                .document(etId.text.toString())
                .update(updates)
                .addOnSuccessListener {
                    loadUsers()
                }
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            db.collection("users")
                .document(etId.text.toString())
                .delete()
                .addOnSuccessListener {
                    loadUsers()
                }
        }

        loadUsers()
    }

    private fun loadUsers() {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->

                userList.clear()

                for (doc in result) {
                    val user = User(
                        id = doc.id,
                        name = doc.getString("name") ?: "",
                        age = doc.getLong("age")?.toInt() ?: 0,
                        city = doc.getString("city") ?: ""
                    )
                    userList.add(user)
                }

                recyclerView.adapter = UserAdapter(userList)
            }
    }
}