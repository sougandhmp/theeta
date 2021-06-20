package com.android.theta.user.model

import androidx.recyclerview.widget.DiffUtil

class Cart(var count : Int,var CartItem:List<Item>) {

    object CartDiff : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem.desc== newItem.desc
    }
}