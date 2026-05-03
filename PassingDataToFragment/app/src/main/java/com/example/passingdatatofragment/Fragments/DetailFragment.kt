import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.passingdatatofragment.R

class DetailFragment : Fragment() {
    // ============================
// Factory Pattern — newInstance()
// ============================
// NEVER use constructor to pass data!
// Bundle survives screen rotation, constructor does NOT.
    companion object {
        fun newInstance(itemName: String): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putString("item", itemName)
            fragment.arguments = bundle // ✅ Attach data safely
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
    // ============================
// Retrieve data from Bundle
// ============================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// ✅ Read the data that was passed via newInstance()
        val item = arguments?.getString("item") ?: "No data received"
        view.findViewById<TextView>(R.id.detailText).text = "You selected $item"
    }
}