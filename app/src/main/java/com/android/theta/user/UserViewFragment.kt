package com.android.theta.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.theta.R
import com.android.theta.databinding.UserViewFragmentBinding


class UserViewFragment : Fragment() {

    companion object {
        fun newInstance() = UserViewFragment()
    }

    private lateinit var viewModel: UserViewModel
    private lateinit var binding: UserViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserViewFragmentBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // TODO: Use the ViewModel
    }
    override fun onStart() {
        super.onStart()
        navigateToVendor()
    }

    private fun navigateToVendor() {

        binding.action1.setOnClickListener {

            findNavController().navigate(R.id.action_userView_to_itemList)
        }

    }


}