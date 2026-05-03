import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.passingdatatofragment.R

class ProfileFragment : Fragment() {
    companion object {
        // Key constants — used to put and get data from the Bundle
        private const val ARG_NAME = "user_name"
        private const val ARG_ID = "user_id"
        // newInstance() = the CORRECT way to create a fragment with data
// Never do: ProfileFragment("Ali", 42) — WRONG!
        fun newInstance(name: String, userId: Int): ProfileFragment {
            val fragment = ProfileFragment() // create fragment object
            val args = Bundle() // Bundle = key-value store (likea bag)
            args.putString(ARG_NAME, name) // put name with key "user_name"
            args.putInt(ARG_ID, userId) // put id with key "user_id"
            fragment.arguments = args // attach the bag to the fragment
            return fragment // return ready-to-use fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// Retrieve data safely from the bundle using the same keys
        val name = arguments?.getString(ARG_NAME) ?: "Unknown"
        val userId = arguments?.getInt(ARG_ID) ?: -1
// Now use the data — for example show it in a TextView
        view.findViewById<TextView>(R.id.tvname).text = "Hello, $name!"
    }
}