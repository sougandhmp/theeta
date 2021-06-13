package com.android.theta.user.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.theta.databinding.RawUserViewBinding
import com.android.theta.user.model.Vendor
import java.util.logging.Logger

class UserViewAdapter : ListAdapter<Vendor, UserViewAdapter.UserViewHolder>(Vendor.VendorDiff) {

    companion object {
        val LOG = Logger.getLogger(UserViewAdapter::class.java.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = RawUserViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UserViewHolder(binding);
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class UserViewHolder(private val binding: RawUserViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.vendor?.let { vendor ->
                    navigateToPostDetail(it, vendor.id)
                }
            }
        }

        private fun navigateToPostDetail(view: View, id: Int) {
            LOG.info("[UserViewAdapter] [navigateToPostDetail] with id : $id")
            val action = UserViewFragmentDirections.actionUserViewToItemList(id);
            view.findNavController().navigate(action)
            /* val extras = FragmentNavigatorExtras(view to view.context.getString(R.string.transition_category_list))
             val action =
                 CategoryListFragmentDirections.actionCategoryListFragmentToCategoryFragment(id)
             view.findNavController().navigate(action, extras)*/
        }

        fun bind(vendor: Vendor) {
            binding.run {
                this.vendor = vendor
                executePendingBindings()
            }
        }
    }


}