package com.android.theta.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.theta.databinding.RawDetailCartBinding
import com.android.theta.databinding.RawDetailItemBinding
import com.android.theta.user.model.Item

class CartAdapter : ListAdapter<Item, CartAdapter.CartViewHolder>(Item.ItemDiff) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        var binding=RawDetailCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartAdapter.CartViewHolder(binding);
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CartViewHolder(private val binding: RawDetailCartBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.setClickListener {
                binding.item?.let { item ->
                    // TODO navigation
                }
            }
        }

        fun bind(item: Item) {
            binding.run {
                this.item = item
                executePendingBindings()
            }
        }

    }


}