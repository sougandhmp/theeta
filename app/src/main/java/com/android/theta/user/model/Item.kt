package com.android.theta.user.model

import androidx.recyclerview.widget.DiffUtil

data class Item(var id:Int,var name:String,var desc:String,var  price:String,var serveCount:String,var imgSrc:String,var  rating:Int) {

    object ItemDiff : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem.desc== newItem.desc
    }
}