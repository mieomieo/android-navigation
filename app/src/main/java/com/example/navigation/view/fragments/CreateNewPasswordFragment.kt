package com.example.navigation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigation.R
import com.example.navigation.databinding.FragmentCreateNewPasswordBinding

class CreateNewPasswordFragment : Fragment() {
    private lateinit var binding: FragmentCreateNewPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNewPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }



}