package com.example.passingdatatofragment

import HomeFragment
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.passingdatatofragment.Fragments.ListFragment

class MainActivity : AppCompatActivity(), ListFragment.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Find the buttons from our layout
        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnList = findViewById<Button>(R.id.btnList)
// ✅ IMPORTANT RULE (from your manual):
// Only load the first fragment if the Activity is created for the FIRST time.
// savedInstanceState is NOT null when device rotates.
// Without this check, a second copy of the fragment loads on rotation!
        if (savedInstanceState == null) {
            loadFragment(HomeFragment()) // Load Home as the default screen
        }
// When the Home button is clicked, load HomeFragment
        btnHome.setOnClickListener {
            loadFragment(HomeFragment())
        }
        btnProfile.setOnClickListener {
// Pass name and ID to ProfileFragment correctly
            val fragment = ProfileFragment.newInstance("Ali", 42)
            loadFragment(fragment)
        }
        btnList.setOnClickListener {
            loadFragment(ListFragment())
        }
// When the Profile button is clicked, load ProfileFragment
    }
    // It takes any Fragment object and shows it in our container.
    private fun loadFragment(fragment: Fragment) {
// Step 1: Get the FragmentManager
// FragmentManager = the manager class that knows about all fragments
        val manager = supportFragmentManager
// Step 2: Begin a transaction
// A transaction = a group of fragment operations treated as ONE unit
        val transaction = manager.beginTransaction()
// Step 3: replace()
// This REMOVES the current fragment from the container
// and ADDS the new fragment in its place.
// R.id.fragmentContainer = the FrameLayout id from activity_main.xml
        transaction.replace(R.id.fragmentContainer, fragment)
// Step 4: addToBackStack(null)
// This RECORDS the transaction in history.
// So when the user presses the BACK button,
// the previous fragment is restored (not the whole app closing).
// null = no name given to this back stack entry
        transaction.addToBackStack(null)
// Step 5: commit()
// This APPLIES all the operations above.
// Nothing happens until commit() is called!
        transaction.commit()
    }
    override fun onItemSelected(itemName: String) {
        val detailFragment = DetailFragment.newInstance(itemName)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}