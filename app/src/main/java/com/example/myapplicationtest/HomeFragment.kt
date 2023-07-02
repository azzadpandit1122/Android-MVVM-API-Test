package com.example.myapplicationtest

import CommanListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationtest.databinding.FragmentHomeBinding
import com.example.myapplicationtest.remote.NetworkResult
import com.example.myapplicationtest.response.UserData
import com.example.myapplicationtest.utils.CustomProgressDialog
import com.example.myapplicationtest.utils.toast
import java.util.*


class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val progressDialog by lazy { CustomProgressDialog(requireContext()) }
    private val viewModel: HomeViewModel by viewModels()
    private var result : UserData? = null
    var adapter :CommanListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserDataResponse()

        viewModel.getUserData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Loading -> {
                    progressDialog.start()
                }
                is NetworkResult.Error -> {
                    progressDialog.stop()
                    toast { response.message.toString() }
                }
                is NetworkResult.Success -> {
                    progressDialog.stop()
                    response.data?.let {
                        result = it
                        updateUI(it)
                    }
                }
            }
        }

        getSearchMethords()
    }

    private fun getSearchMethords() {
        binding.searchBar.isActivated = true
        binding.searchBar.queryHint = "Type your keyword here"
        binding.searchBar.onActionViewExpanded()
        binding.searchBar.isIconified = false
        binding.searchBar.clearFocus()

        binding.searchBar.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText.toString())
                return false
            }

        })


    }
    private fun filter(text: String) {
        val filterdNames: ArrayList<UserData.Result> = ArrayList()
        if (!result?.results.isNullOrEmpty())
        {
            for (s in result?.results!!) {
                if (s.name.toLowerCase().contains(text.lowercase(Locale.getDefault()))) {
                    filterdNames.add(s)
                }
            }
        }

        adapter?.filterdNames(filterdNames)
    }

    private fun updateUI(data: UserData) {
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.VERTICAL,false)
        data?.let {
            adapter  = CommanListAdapter(it.results)
            binding.recyclerView.adapter =adapter
            adapter?.notifyDataSetChanged()
        }
    }

}