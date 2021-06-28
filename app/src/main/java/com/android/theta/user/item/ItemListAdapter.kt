package com.android.theta.user.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.theta.commons.CustomOnClickListener
import com.android.theta.databinding.RawDetailItemBinding
import com.android.theta.user.model.Item

class ItemListAdapter(private val listener:CustomOnClickListener) : ListAdapter<Item, ItemListAdapter.ItemViewHolder>(Item.ItemDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding=
            RawDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding);
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position),listener)
    }

    class ItemViewHolder(private val binding: RawDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {




        fun bind(item: Item, listener: CustomOnClickListener) {
            binding.run {
                this.item = item
                this.clickListener=listener;
                executePendingBindings()
            }


        }

    }


}