package com.android.theta.user.model

import androidx.recyclerview.widget.DiffUtil

data class Item(
    var id: String,
    var name: String,
    var desc: String = "",
    var price: Long,
    var serveCount: String = "",
    var imgSrc: String,
    var rating: Long
) {

    object ItemDiff : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem.desc == newItem.desc
    }
}