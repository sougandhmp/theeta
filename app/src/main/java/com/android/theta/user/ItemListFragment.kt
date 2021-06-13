package com.android.theta.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.theta.databinding.ItemListFragmentBinding
import com.android.theta.user.model.Item
import com.android.theta.user.model.Vendor
import dagger.hilt.android.AndroidEntryPoint
import observe


@AndroidEntryPoint
class ItemListFragment : Fragment() {

    companion object {
        fun newInstance() = ItemListFragment()
    }

    private  val viewModel by viewModels<ItemListViewModel>()
    private lateinit var binding: ItemListFragmentBinding

    private  val itemListAdapter= ItemListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setItems()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemListFragmentBinding.inflate(layoutInflater, container, false).apply {

            vendorListRecycler.apply {

                adapter = itemListAdapter
            }
            lifecycleOwner = this@ItemListFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
observe(itemList,::observeItems)

        }




    }

    private fun observeItems(list: List<Item>?) {
        list ?: return
        itemListAdapter.submitList(list)
    }






}