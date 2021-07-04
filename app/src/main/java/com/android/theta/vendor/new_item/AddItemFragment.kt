package com.android.theta.vendor.new_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.theta.databinding.AddItemFragmentBinding
import com.android.theta.vendor.model.VendorItem

class AddItemFragment : Fragment() {


    private val addItemViewModel by viewModels<AddItemViewModel>()
    private lateinit var binding: AddItemFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddItemFragmentBinding.inflate(layoutInflater, container, false).apply {
            lifecycleOwner = this@AddItemFragment
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.addButton.setOnClickListener {
            val item = VendorItem(
                name = binding.itemName.text.toString().trim(),
                rating = 0,
                image_url = binding.imageUrl.text.toString().trim(),
                desc = binding.itemDesc.text.toString().trim(),
                price = binding.price.text.toString().toLong(),
            )
            addItemViewModel.addToDatabase(item)

        }
    }

}