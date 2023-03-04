package com.brujiyaseer.shoppingapp.presentation.destinations.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.brujiyaseer.shoppingapp.R
import com.brujiyaseer.shoppingapp.core.base.BaseFragment
import com.brujiyaseer.shoppingapp.core.utils.Resource
import com.brujiyaseer.shoppingapp.core.utils.snackBar
import com.brujiyaseer.shoppingapp.core.utils.visible
import com.brujiyaseer.shoppingapp.databinding.FragmentHomeBinding
import com.brujiyaseer.shoppingapp.presentation.adapters.MainScreenAdapter
import com.brujiyaseer.shoppingapp.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val adapter = MainScreenAdapter()
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = binding.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).title = ""

        binding.profileImage.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_profile)
        }

        binding.tvSearch.setOnClickListener {
            activity?.findNavController(R.id.nav_host_main)
                ?.navigate(R.id.action_navigation_main_to_navigation_search)
        }

        with(binding) {
            recyclerView.adapter = adapter

            viewModel.homeGoodsLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
//                        Log.d(TAG,  it.value.toString())
                        requireActivity().snackBar("success")
                        progressBar.visible(false)
                        adapter.items = it.value
                    }
                    is Resource.Failure -> {
                        val msg = it.exception.message.toString()
                        Log.d(TAG,  msg)
                        progressBar.visible(false)
                        requireActivity().snackBar(msg)
                    }
                    is Resource.Loading -> progressBar.visible(true)
                }
            }

        }

    }
}