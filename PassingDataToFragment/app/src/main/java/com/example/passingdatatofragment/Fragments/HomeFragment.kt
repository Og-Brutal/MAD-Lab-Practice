import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.passingdatatofragment.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, // tool that reads XML and creates Views
        container: ViewGroup?, // the parent container (FrameLayout) 
        savedInstanceState: Bundle? // saved state (null on first load)
    ): View? {
// inflate() reads fragment_home.xml and returns it as a View
// 'false' means: do NOT attach it to container yet — let FragmentManager do it
        return inflater.inflate(R.layout.fragment_home, container, false)
    } 
// onViewCreated() is called AFTER the view is fully created.
// This is the safe place to access UI elements like TextView, Button, etc.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// You can find and use views here safely
// Example: view.findViewById<TextView>(R.id.tvHome).text = "Hello!"
    }
}
