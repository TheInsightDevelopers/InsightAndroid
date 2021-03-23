import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insights.ItemAdapter
import com.example.insights.R

class HomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        var recyclerView: RecyclerView =root.findViewById(R.id.recyclerview)
        recyclerView.layoutManager= LinearLayoutManager(getContext())
        val itemAdapter= ItemAdapter(this,getItemsList())
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