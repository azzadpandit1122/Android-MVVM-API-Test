import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.R
import com.example.myapplicationtest.databinding.CommanLayoutBinding
import com.example.myapplicationtest.response.UserData
import java.util.*

class CommanListAdapter(private var mList: List<UserData.Result>) : RecyclerView.Adapter<CommanListAdapter.ViewHolder>() {

    private lateinit var binding: CommanLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CommanLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.binding(ItemsViewModel)
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    fun filterdNames(filterdNames: ArrayList<UserData.Result>) {
        mList = filterdNames
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: CommanLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(itemsViewModel: UserData.Result) {
            binding.title.text = itemsViewModel.name

            binding.root.setOnClickListener {
                 val bundle = Bundle()
                 bundle.putString("name",itemsViewModel.id.toString())
                 it.findNavController().navigate(R.id.detailsFragment,bundle)
            }
        }
    }


}
