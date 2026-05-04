package com.example.dbdemo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dbdemo.DB_Helper.DBHelper
import com.example.dbdemo.R

class AddContactFragment : Fragment() {

    lateinit var dbHelper: DBHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)

        dbHelper = DBHelper(requireContext())

        val name = view.findViewById<EditText>(R.id.name)
        val phone = view.findViewById<EditText>(R.id.phone)
        val email = view.findViewById<EditText>(R.id.email)
        val saveBtn = view.findViewById<Button>(R.id.saveBtn)

        saveBtn.setOnClickListener {
            dbHelper.insertContact(
                name.text.toString(),
                phone.text.toString(),
                email.text.toString()
            )
            Toast.makeText(requireContext(),"Contact Added", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}