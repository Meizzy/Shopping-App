package com.brujiyaseer.shoppingapp.presentation.authentication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.brujiyaseer.shoppingapp.R
import com.brujiyaseer.shoppingapp.core.base.BaseFragment
import com.brujiyaseer.shoppingapp.core.utils.snackBar
import com.brujiyaseer.shoppingapp.databinding.FragmentLoginBinding
import com.brujiyaseer.shoppingapp.presentation.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: SignInViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            viewModel.getLoggedInUser().observe(viewLifecycleOwner) {
                if (it != null) {
                    findNavController().navigate(R.id.action_navigation_login_to_navigation_main)
                } else {
                    requireActivity().apply {
                        snackBar(getString(R.string.doesnt_exist)) {
                            supportFragmentManager.popBackStack()
                        }
                    }
                }
            }
        }
    }
}