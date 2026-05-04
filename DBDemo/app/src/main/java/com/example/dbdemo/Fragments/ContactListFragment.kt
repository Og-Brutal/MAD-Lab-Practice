package com.example.dbdemo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbdemo.Adapter.ContactAdapter
import com.example.dbdemo.DB_Helper.DBHelper
import com.example.dbdemo.Models.Contact
import com.example.dbdemo.R


class ContactListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var dbHelper: DBHelper
    lateinit var list: ArrayList<Contact>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        dbHelper = DBHelper(requireContext())

        list = ArrayList()

        val cursor = dbHelper.getAllContacts()

        if(cursor.moveToFirst()){
            do {
                list.add(
                    Contact(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    )
                )
            } while(cursor.moveToNext())
        }

        val adapter = ContactAdapter(list) {
            val fragment = ContactDetailFragment(it)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        return view
    }
}