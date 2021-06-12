package com.android.theta.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.theta.databinding.RowUserViewBinding
import com.android.theta.user.model.Hotel

class UserViewAdapter : ListAdapter<Hotel, UserViewAdapter.UserViewHolder>(Hotel.HotelDiff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = RowUserViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UserViewHolder(binding);
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class UserViewHolder(private val binding: RowUserViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.hotel?.let { hotel ->
                    navigateToPostDetail(it, hotel.id)
                }
            }
        }

        private fun navigateToPostDetail(view: View, id: Int) {
            /* val extras = FragmentNavigatorExtras(view to view.context.getString(R.string.transition_category_list))
             val action =
                 CategoryListFragmentDirections.actionCategoryListFragmentToCategoryFragment(id)
             view.findNavController().navigate(action, extras)*/
        }

        fun bind(hotel: Hotel) {
            binding.run {
                this.hotel = hotel
                executePendingBindings()
            }
        }
    }


}




