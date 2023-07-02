import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationtest.R
import com.example.myapplicationtest.databinding.ItemLayoutBinding

class ItemAdapter(private var mList: List<String>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private lateinit var binding: ItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.binding(ItemsViewModel)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(itemsViewModel: String) {
            binding.tvName.text = itemsViewModel
/*

            Glide.with(binding.root.context)
                .load(itemsViewMode) // image url
                .centerCrop()
                .into(binding.ivLogo);  // imageview object
*/


           /* binding.root.setOnClickListener {
                 val bundle = Bundle()
                 bundle.putString("name",itemsViewModel.name)
                 it.findNavController().navigate(R.id.detailsFragment,bundle)
            }*/
        }
    }


}
