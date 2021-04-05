import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insights.VideoRecyclerViewAdapter
import com.example.insights.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val videoValues = ArrayList<HashMap<String,String>>()
        val root2 = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView2: RecyclerView = root2.findViewById<RecyclerView>(R.id.recyclerview_Home)
        recyclerView2.layoutManager= LinearLayoutManager(context)
        val db = FirebaseFirestore.getInstance()
        db.collection("Videos").addSnapshotListener { value, error ->
            if (error == null) {
                if (value != null) {
                    for (change in value.documentChanges) {
                        if (change.type == DocumentChange.Type.ADDED) {
                            videoValues.add(change.document.data as HashMap<String, String>)
                            val VideoRecyclerViewAdapter = VideoRecyclerViewAdapter(this, videoValues)
                            recyclerView2.adapter = Itemadapter
                        }
                    }
                }
            }
        }


        return root2
    }



}
