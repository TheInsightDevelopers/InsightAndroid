import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insights.BookRecylerViewAdapter
import com.example.insights.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class BookFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val bookvalues = ArrayList<HashMap<String, String>>()

        val root = inflater.inflate(R.layout.fragment_book, container, false)

        var recyclerView: RecyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        //val BookRecylerViewAdapter= BookRecylerViewAdapter(this,bookvalues)
        //recyclerView.adapter=(BookRecylerViewAdapter)
        val db = FirebaseFirestore.getInstance()
        db.collection("Books").addSnapshotListener { value, error ->
            if (error == null) {
                if (value != null) {
                    for (change in value.documentChanges) {
                        if (change.type == DocumentChange.Type.ADDED) {
                            bookvalues.add(change.document.data as HashMap<String, String>)
                            val BookRecylerViewAdapter = BookRecylerViewAdapter(this, bookvalues)
                            recyclerView.adapter = BookRecylerViewAdapter
                        }
                    }
                }
            }
        }

        return root
    }


}