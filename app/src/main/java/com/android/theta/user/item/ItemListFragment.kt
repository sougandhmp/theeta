package com.android.theta.user.item

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.theta.MainActivityViewModel
import com.android.theta.R
import com.android.theta.commons.CustomOnClickListener
import com.android.theta.commons.observe
import com.android.theta.databinding.ItemListFragmentBinding
import com.android.theta.user.model.Item
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemListFragment : Fragment(),CustomOnClickListener {

    private val viewModel by viewModels<ItemListViewModel>()
    private val activityViewModel by activityViewModels<MainActivityViewModel>();
    private lateinit var binding: ItemListFragmentBinding
    private val args by navArgs<ItemListFragmentArgs>()

    private val itemListAdapter = ItemListAdapter(this@ItemListFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setItems(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemListFragmentBinding.inflate(layoutInflater, container, false).apply {
            vendorListRecycler.apply {
                adapter = itemListAdapter
            }
            lifecycleOwner = this@ItemListFragment
        }
        handleAppBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.apply {
            observe(itemList, ::observeItems)
        }
    }

    private fun observeItems(list: List<Item>?) {
        list ?: return
        itemListAdapter.submitList(list)
    }

    private fun handleAppBar(){
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.badge_search -> {
                    Snackbar.make(binding.root, "Search button clicked", Snackbar.LENGTH_LONG)
                        .show()
                    true
                }
                R.id.badge_cart -> {
                    findNavController().navigate(R.id.action_itemListFragment_to_CartFragment)
                    true
                }
                else -> super.onOptionsItemSelected(it)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(cart: Item) {
        Snackbar.make(binding.root, "Added ${cart.name} to Cart ", Snackbar.LENGTH_LONG)
            .show()
        activityViewModel.addItem(cart)
    }




}