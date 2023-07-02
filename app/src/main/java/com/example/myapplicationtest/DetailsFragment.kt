package com.example.myapplicationtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapplicationtest.adapter.MyAdapter
import com.example.myapplicationtest.databinding.FragmentDetailsBinding
import com.example.myapplicationtest.remote.NetworkResult
import com.example.myapplicationtest.response.GetNameInfoResponses
import com.example.myapplicationtest.utils.CustomProgressDialog
import com.example.myapplicationtest.utils.toast

class DetailsFragment : Fragment() {

    private val binding by lazy { FragmentDetailsBinding.inflate(layoutInflater) }
    private val progressDialog by lazy { CustomProgressDialog(requireContext()) }
    private val viewModel: DetailsViewModel by viewModels()
    var receivedName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receivedName = arguments?.getString("name")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNameInfoResponse(receivedName.toString())

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
                        updateUi(it)
                    }
                }
            }
        }
    }

    private fun updateUi(results: GetNameInfoResponses) {

        Glide.with(binding.root.context)
            .load(results.image) // image url
            .centerCrop()
            .into(binding.ivLogo);  // imageview object

        binding.tvName.text = results.name
        binding.tvAboutme.text = results.status
        binding.tvLocation.text = results.location.name
        binding.tvProfile.text = results.type
        binding.tvEmailId.text = results.name + "@gmail.com"
        binding.tvSpecie.text = results.species
        binding.tvEpisode.text = results.episode.size.toString()


        results.episode?.let {
            binding.rvEpisodes.text = it.toString()
        }


    }

}