import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        var recyclerView: RecyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager= LinearLayoutManager(context)
        val itemAdapter= ItemAdapter(this,getItems())
        recyclerView.adapter=(itemAdapter)

        return root
    }


    private fun getItems(): ArrayList<HashMap<String,String>> {
        val testhashmap = hashMapOf("Title" to "Amar's Books")
        FirebaseFirestore.getInstance().collection("Books").document("testBook").set(testhashmap).addOnCompleteListener { task ->
            if(task.isSuccessful){
            }
        }
            val bookslist = ArrayList<HashMap<String,String>>()

            FirebaseFirestore.getInstance().collection("Books").get().addOnSuccessListener { books ->
                for(book in books){
                    bookslist.add(book.data as HashMap<String, String>)
                }
            }.addOnFailureListener { exception ->
                Log.d(TAG,"Error in getting documents", exception)
            }
            bookslist.add(hashMapOf("Title" to "Sample book"))
        return bookslist
    }

}