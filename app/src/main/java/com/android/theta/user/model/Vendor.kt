package com.android.theta.user.model

import androidx.recyclerview.widget.DiffUtil

data class Vendor(
     var id: String,
     var name: String,
     var styleName: String,
     var avgPriceForTwo: String,
     var imgUrl: String
) {
     object VendorDiff : DiffUtil.ItemCallback<Vendor>() {
          override fun areItemsTheSame(oldItem: Vendor, newItem: Vendor) = oldItem.name == newItem.name
          override fun areContentsTheSame(oldItem: Vendor, newItem: Vendor) = oldItem.id== newItem.id
     }
}