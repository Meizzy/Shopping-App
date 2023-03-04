package com.brujiyaseer.shoppingapp.presentation.authentication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.brujiyaseer.shoppingapp.R
import com.brujiyaseer.shoppingapp.core.base.BaseFragment
import com.brujiyaseer.shoppingapp.core.utils.isValidEmail
import com.brujiyaseer.shoppingapp.core.utils.snackBar
import com.brujiyaseer.shoppingapp.databinding.FragmentSignInBinding
import com.brujiyaseer.shoppingapp.presentation.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "SignInFragment"
@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val viewModel: SignInViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logIn.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_sign_in_to_navigation_login2)
        }
        binding.button.setOnClickListener {
            viewModel.getLoggedInUser().observe(viewLifecycleOwner) {
                if (it != null) {
                    Log.d(TAG, "user found with name ${it.email}")
                    requireActivity().snackBar(getString(R.string.already_exist))
                } else {
                    Log.d(TAG, "user not found")

                    val firstName = binding.firstName.text.toString()
                    val lastName = binding.lastName.text.toString()
                    val email = binding.email.text.toString()
                    if (email.isValidEmail()) {
                        viewModel.saveUser(firstName, lastName, email)
                        findNavController().navigate(R.id.action_navigation_sign_in_to_navigation_main)
                    } else {
                        requireActivity().snackBar(getString(R.string.invalid_email))
                    }
                }

            }

        }
    }
}