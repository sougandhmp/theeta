package com.android.theta.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.theta.commons.CustomRepeatListener
import com.android.theta.databinding.RawDetailCartBinding
import com.android.theta.user.model.ItemCart

class CartAdapter(private val listener: CustomRepeatListener) :
    ListAdapter<ItemCart, CartAdapter.CartViewHolder>(ItemCart.ItemDiff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        var binding =
            RawDetailCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartAdapter.CartViewHolder(binding);
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        holder.bind(getItem(position),listener)
    }

    class CartViewHolder(private val binding: RawDetailCartBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: ItemCart, listener: CustomRepeatListener) {
            binding.run {
                this.cart = item
                this.clickListener=listener;
                executePendingBindings()
            }
        }

    }


}