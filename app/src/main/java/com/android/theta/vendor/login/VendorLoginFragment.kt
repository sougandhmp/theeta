package com.android.theta.vendor.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.theta.R
import com.android.theta.databinding.FragmentVendorLoginBinding


class VendorLoginFragment : Fragment() {


    private lateinit var binding: FragmentVendorLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVendorLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
            binding.loginButton.setOnClickListener {
                login()
            }
    }

    private fun login() {
        findNavController().navigate(R.id.action_VendorLoginFragment_to_VendorViewFragment)
    }

}