package com.android.theta.vendor.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.theta.R
import com.android.theta.commons.observe
import com.android.theta.databinding.VendorViewFragmentBinding
import com.android.theta.vendor.model.VendorItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VendorViewFragment : Fragment() {


    private val vendorViewModel by viewModels<VendorViewViewModel>()
    private lateinit var binding: VendorViewFragmentBinding

    private var vendorViewAdapter = VendorViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vendorViewModel.setItems()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = VendorViewFragmentBinding.inflate(layoutInflater, container, false).apply {
            vendorListRecycler.apply {
                adapter = vendorViewAdapter
            }
            lifecycleOwner = this@VendorViewFragment
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        onlineSwitch()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        vendorViewModel.apply {
            observe(vendorView, ::observeItems)
        }
    }

    private fun observeItems(list: List<VendorItem>?) {
        list ?: return
        vendorViewAdapter.submitList(list)
    }
    private fun onlineSwitch() {
        binding.switchButton.setOnCheckedChangeListener { buttonView, isChecked ->
            run {
                if (isChecked)
                    buttonView.text = getString(R.string.store_online)
                 else
                    buttonView.text = getString(R.string.store_offline)

            }
        }
    }

}