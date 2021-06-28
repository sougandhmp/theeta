package com.android.theta.vendor.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.theta.databinding.RawVendorItemViewBinding
import com.android.theta.vendor.model.VendorItem

class VendorViewAdapter : ListAdapter<VendorItem, VendorViewAdapter.VendorViewHolder>(VendorItem.ItemDiff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VendorViewHolder {
        val binding=
            RawVendorItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VendorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VendorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class VendorViewHolder(private val binding:RawVendorItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(vendorView: VendorItem) {
            binding.run {
                this.vendorView = vendorView
                executePendingBindings()
            }
        }

    }



}
