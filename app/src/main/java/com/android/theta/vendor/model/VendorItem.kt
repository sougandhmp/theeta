package com.android.theta.vendor.model

import androidx.recyclerview.widget.DiffUtil

data class VendorItem(
    var id: Int,
    var name: String?,
    var desc: String?,
    var price: String?,
    var serveCount: String?,
    var imgSrc: String?,
    var count: Int,
    var defaultStatus: Boolean
) {


    object ItemDiff : DiffUtil.ItemCallback<VendorItem>() {
        override fun areItemsTheSame(oldItem: VendorItem, newItem: VendorItem) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: VendorItem, newItem: VendorItem) =
            oldItem.desc == newItem.desc
    }
}