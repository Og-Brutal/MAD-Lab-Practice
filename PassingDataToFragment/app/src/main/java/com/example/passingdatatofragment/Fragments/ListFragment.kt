package com.example.passingdatatofragment.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.passingdatatofragment.R

class ListFragment : Fragment() {
    // ============================
// STEP A: Define the Interface
// ============================
// This is the "contract" — any Activity that hosts this fragment
// MUST implement this interface, or the app crashes.
    interface OnItemSelectedListener {
        fun onItemSelected(itemName: String)
    }
    // This holds the reference to the Activity (as the listener)
    private var listener: OnItemSelectedListener? = null
    // ============================
// STEP B: Connect in onAttach()
// ============================
// onAttach() is called FIRST when fragment connects to Activity.
// We cast the Activity to our interface here.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemSelectedListener) {
            listener = context // ✅ Activity implements our interface
        } else {
            throw ClassCastException("$context must implement OnItemSelectedListener")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    // ============================
// STEP C: Fire the callback`
// ============================
// When user clicks a button, we send the data UP to the Activity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnItem1).setOnClickListener {
            listener?.onItemSelected("Apple") // 🔥 Fire callback with
            "Apple"
        }
        view.findViewById<Button>(R.id.btnItem2).setOnClickListener {
            listener?.onItemSelected("Banana") // 🔥 Fire callback with
            "Banana"
        }
        view.findViewById<Button>(R.id.btnItem3).setOnClickListener {
            listener?.onItemSelected("Orange") // 🔥 Fire callback with
            "Orange"
        }
    }
    // ============================
// STEP D: Clean up in onDetach()
// ============================
// Prevent memory leaks by releasing the Activity reference
    override fun onDetach() {
        super.onDetach()
        listener = null // ✅ No more reference to Activity
    }
}