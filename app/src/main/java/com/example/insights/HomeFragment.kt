import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.insights.R
import com.example.insights.androidDevelopment1
import java.util.zip.Inflater

class HomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val imageButton1 : ImageButton =root.findViewById(R.id.androidDevelopmentImageButton1)
        imageButton1.setOnClickListener{
            val intent = Intent(activity,androidDevelopment1::class.java)
            startActivity(intent)
        }

        return root
    }
}