package com.example.contactapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.Models.Contact

class RecyclerViewAdapter(private val contactList: MutableList<Contact>):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       val view:View= LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val contact=contactList[position]

        holder.name.text=contact.name
        holder.phone.text=contact.phone
    }

    override fun getItemCount(): Int {
     return contactList.size
    }

    class ViewHolder(val contacrtView: View): RecyclerView.ViewHolder(contacrtView){
        public var name=contacrtView.findViewById<TextView>(R.id.nameText)
        public var phone=contacrtView.findViewById<TextView>(R.id.phoneText)
        public var callbtn=contacrtView.findViewById<TextView>(R.id.callBtn)
        public var editBtn=contacrtView.findViewById<TextView>(R.id.editBtn)
        public var deleteBtn=contacrtView.findViewById<TextView>(R.id.deleteBtn)


    }

}