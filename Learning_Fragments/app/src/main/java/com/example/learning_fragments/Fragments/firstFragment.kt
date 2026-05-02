package com.example.learning_fragments.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.learning_fragments.MainActivity2
import com.example.learning_fragments.R

class firstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view= inflater.inflate(R.layout.fragment_first, container, false)

        val textview=view.findViewById<TextView>(R.id.t2)

        textview.setOnClickListener {
            val intent= Intent(requireContext(), MainActivity2::class.java)
            startActivity(intent)
        }


        return view;
    }


}