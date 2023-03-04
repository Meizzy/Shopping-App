package com.brujiyaseer.shoppingapp.presentation.destinations

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.brujiyaseer.shoppingapp.R
import com.brujiyaseer.shoppingapp.core.base.BaseFragment
import com.brujiyaseer.shoppingapp.databinding.AppBarMainBinding

class ContentMainFragment : BaseFragment<AppBarMainBinding>(AppBarMainBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController =
            requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
        super.onViewCreated(view, savedInstanceState)
    }
}