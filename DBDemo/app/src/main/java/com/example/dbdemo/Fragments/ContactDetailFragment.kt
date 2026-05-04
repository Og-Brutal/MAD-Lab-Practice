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
import com.example.dbdemo.Models.Contact
import com.example.dbdemo.R

class ContactDetailFragment(val contact: Contact) : Fragment() {

    lateinit var dbHelper: DBHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_contact_detail, container, false)

        dbHelper = DBHelper(requireContext())

        val name = view.findViewById<EditText>(R.id.name)
        val phone = view.findViewById<EditText>(R.id.phone)
        val email = view.findViewById<EditText>(R.id.email)
        val editBtn = view.findViewById<Button>(R.id.editBtn)
        val deleteBtn = view.findViewById<Button>(R.id.deleteBtn)

        name.setText(contact.name)
        phone.setText(contact.phone)
        email.setText(contact.email)

        editBtn.setOnClickListener {
            dbHelper.updateContact(
                contact.id,
                name.text.toString(),
                phone.text.toString(),
                email.text.toString()
            )
            Toast.makeText(requireContext(),"Updated", Toast.LENGTH_SHORT).show()
        }

        deleteBtn.setOnClickListener {
            dbHelper.deleteContact(contact.id)
            Toast.makeText(requireContext(),"Deleted",Toast.LENGTH_SHORT).show()
        }

        return view
    }
}