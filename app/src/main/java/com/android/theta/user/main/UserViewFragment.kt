package com.android.theta.user.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.theta.R
import com.android.theta.commons.observe
import com.android.theta.databinding.UserViewFragmentBinding
import com.android.theta.user.model.Vendor
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserViewFragment : Fragment() {

    private val userViewModel by viewModels<UserViewModel>()
    private lateinit var binding: UserViewFragmentBinding

    private var userViewAdapter = UserViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel.setHotelList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserViewFragmentBinding.inflate(layoutInflater, container, false).apply {
            vendorListRecycler.apply {
                adapter = userViewAdapter
            }
            lifecycleOwner = this@UserViewFragment
        }
        handleAppBar()
        //   userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return binding.root
    }

    private fun handleAppBar() {
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.badge_search -> {
                    Snackbar.make(binding.root, "Search button clicked", Snackbar.LENGTH_LONG)
                        .show()
                    true
                }
                R.id.badge_cart -> {
                    Snackbar.make(binding.root, "Cart button clicked", Snackbar.LENGTH_LONG)
                        .show()
                    true
                }
                else -> super.onOptionsItemSelected(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        userViewModel.apply {
            observe(vendors, ::observeHotel)
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