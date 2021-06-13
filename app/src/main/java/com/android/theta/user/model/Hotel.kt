package com.android.theta.user.model

import androidx.recyclerview.widget.DiffUtil

data class Hotel(
     var id: Int,
     var name: String,
     var styleName: String,
     var avgPriceForTwo: String,
     var imgUrl: String
) {
     object HotelDiff : DiffUtil.ItemCallback<Hotel>() {
          override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel) = oldItem.name == newItem.name
          override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel) = oldItem.id== newItem.id
     }
}