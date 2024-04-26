package com.example.navigation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.navigation.R
import com.example.navigation.databinding.FragmentLoginBinding
import com.example.navigation.viewmodel.UserViewModel
import com.example.navigation.common.Validate
import com.example.navigation.common.hashSHA256


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(
            this,
            UserViewModel.UserViewModelFactory(requireActivity().application)
        )[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.loginResult.observe(viewLifecycleOwner) { loginResult ->
            if (loginResult != null) {
                findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
                Toast.makeText(requireContext(), "Successfully logged in", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Wrong email or password", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passEt.text.toString()
            if (Validate.isValidEmail(email) && Validate.isValidPassword(password)) {
                userViewModel.loginUser(email, hashSHA256(password))
            }

        }
        binding.registerBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.forgotPasswordBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
    }

}