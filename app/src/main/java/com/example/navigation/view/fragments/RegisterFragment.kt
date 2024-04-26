package com.example.navigation.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.navigation.R
import com.example.navigation.common.Validate
import com.example.navigation.common.hashSHA256
import com.example.navigation.databinding.FragmentRegisterBinding
import com.example.navigation.model.User
import com.example.navigation.viewmodel.UserViewModel

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
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
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGoToLogin.setOnClickListener {
            it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.mailEt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val email = binding.mailEt.text.toString()
                userViewModel.checkUniqueEmail(email)
                if (!Validate.isValidEmail(email)) {
                    binding.mailEt.error = "Invalid email"
                }
                userViewModel.isEmailExisted.observe(viewLifecycleOwner) { isEmailExisted ->
                    if(isEmailExisted > 0){
                        binding.mailEt.error = "Email is already existed"
                    }
                }
            }

        }
        binding.passEt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val password = binding.passEt.text.toString()
                if (!Validate.isValidPassword(password)) {
                    binding.passEt.error = "Password must be at least 8 characters long."
                }
            }
        }
        binding.confirmPassEt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val confirmPassword = binding.confirmPassEt.text.toString()
                val password = binding.passEt.text.toString()
                if (confirmPassword != password) {
                    binding.confirmPassEt.error = "Passwords do not match."
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.mailEt.text.toString()
            val password = binding.passEt.text.toString()
            val confirmPassword = binding.confirmPassEt.text.toString()
            if (Validate.isValidEmail(email) && Validate.isValidPassword(password) && password == confirmPassword) {
                val user = User(email = email, password = hashSHA256(password))
                userViewModel.registerUser(user)
                Toast.makeText(
                    requireContext(), "Inserted new user successfully", Toast.LENGTH_LONG
                ).show()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                if (!Validate.isValidEmail(email)) {
                    binding.mailEt.error = "Invalid email"
                }
                if (!Validate.isValidPassword(password)) {
                    Toast.makeText(
                        requireContext(), "Password must be at least 8 characters long.", Toast.LENGTH_SHORT
                    ).show()
                }
                if (password != confirmPassword) {
                    binding.confirmPassEt.error = "Passwords do not match"
                }
            }
        }
    }


}