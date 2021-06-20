package com.android.theta.user.Item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.theta.databinding.RawDetailItemBinding
import com.android.theta.user.model.Item

class ItemListAdapter : ListAdapter<Item, ItemListAdapter.ItemViewHolder>(Item.ItemDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var binding=
            RawDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListAdapter.ItemViewHolder(binding);
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(private val binding: RawDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

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