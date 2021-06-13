package com.android.theta.user.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.theta.R
import com.android.theta.databinding.UserViewFragmentBinding
import com.android.theta.user.model.Vendor
import dagger.hilt.android.AndroidEntryPoint
import observe

@AndroidEntryPoint
class UserViewFragment : Fragment() {

    companion object {
        fun newInstance() = UserViewFragment()
    }

    private val userViewModel by viewModels<UserViewModel>()
    private lateinit var binding: UserViewFragmentBinding

    private  var userViewAdapter = UserViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel.setHotelList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserViewFragmentBinding.inflate(layoutInflater, container, false).apply {

            vendorListRecycler.apply {

                adapter = userViewAdapter
            }
            lifecycleOwner = this@UserViewFragment
        }
     //   userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.apply {
            observe(vendors,::observeHotel);
        }
    }

    private fun observeHotel(list: List<Vendor>?) {
        list ?: return
        userViewAdapter.submitList(list)
    }
    private fun navigateToVendor() {


     //   binding.
        // binding.action1.setOnClickListener {

        findNavController().navigate(R.id.action_userView_to_itemList)
        // }

    }


}