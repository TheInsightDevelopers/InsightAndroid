import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insights.ItemAdapter
import com.example.insights.R
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bookvalues = ArrayList<HashMap<String,String>>()
        val db = FirebaseFirestore.getInstance()
        db.collection("Books").get().addOnSuccessListener { books ->
            for(book in books){
                bookvalues.add(book.data as HashMap<String, String>)
            }
        }
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        var recyclerView: RecyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager= LinearLayoutManager(context)
        val itemAdapter= ItemAdapter(this,bookvalues)
        recyclerView.adapter=(itemAdapter)

        return root
    }
    private fun getItemsList(): ArrayList<String> {
        val list = ArrayList<String>()

        for(i in 1..15){
            list.add("Item $i")
        }

        return list
    }
}