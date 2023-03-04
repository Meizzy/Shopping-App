package com.brujiyaseer.shoppingapp.presentation.destinations.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.brujiyaseer.shoppingapp.R
import com.brujiyaseer.shoppingapp.core.base.BaseFragment
import com.brujiyaseer.shoppingapp.databinding.FragmentProfileBinding
import com.brujiyaseer.shoppingapp.presentation.viewmodel.MainViewModel
import com.brujiyaseer.shoppingapp.presentation.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: SignInViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            viewModel.deleteUser()
            activity?.findNavController(R.id.nav_host_main)?.navigate(R.id.action_navigation_main_to_navigation_sign_in)
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_home)
        }

        viewModel.getLoggedInUser().observe(viewLifecycleOwner){

            binding.tvFullName.text = getString(R.string.set_user_name,it.firstName , it.lastName)
        }

    }
}