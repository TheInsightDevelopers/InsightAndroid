import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insights.ItemAdapter2
import com.example.insights.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root2 = inflater.inflate(R.layout.fragment_home, container, false)

        var recyclerView2: RecyclerView = root2.findViewById<RecyclerView>(R.id.recyclerview_Home)
        recyclerView2.layoutManager= LinearLayoutManager(context)
        val itemAdapter2= context?.let { ItemAdapter2(it,getItemList()) }
        recyclerView2.adapter=itemAdapter2



        return root2
    }


    private fun getItemList(): ArrayList<String> {
        val list = ArrayList<String>()

        for(i in 1..15){
            list.add("Item $i")
        }

        return list
    }
}
