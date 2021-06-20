package com.android.theta.user.model

import androidx.recyclerview.widget.DiffUtil

data class ItemCart(var name:String?, var desc:String?, var  price:String?, var serveCount:String?, var imgSrc:String?, var  rating:Int) {

constructor(

) : this(null,null,null,null,null,0);

    object ItemDiff : DiffUtil.ItemCallback<ItemCart>() {
        override fun areItemsTheSame(oldItem: ItemCart, newItem: ItemCart) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: ItemCart, newItem: ItemCart) = oldItem.desc== newItem.desc
    }
}