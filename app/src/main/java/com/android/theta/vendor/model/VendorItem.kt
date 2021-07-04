package com.android.theta.vendor.model

import androidx.annotation.Keep
import androidx.recyclerview.widget.DiffUtil
import com.google.firebase.firestore.PropertyName
import java.io.Serializable

@Keep
data class VendorItem(
    var id: String = "",
    @get: PropertyName("item_name") @set: PropertyName("item_name") var name: String = "",
    @get: PropertyName(value = "item_desc") @set: PropertyName(value = "item_desc") var desc: String = "",
    var price: Long = 0,
    var rating: Long = 0,
    var serveCount: String = "",
    @get:PropertyName(value = "image_url") @set:PropertyName(value = "image_url") var image_url: String = "",
    var count: Int = 0,
    var defaultStatus: Boolean = false
) : Serializable {


    object ItemDiff : DiffUtil.ItemCallback<VendorItem>() {
        override fun areItemsTheSame(oldItem: VendorItem, newItem: VendorItem) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: VendorItem, newItem: VendorItem) =
            oldItem.desc == newItem.desc
    }
}