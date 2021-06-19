package com.android.theta.user

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.theta.R
import com.android.theta.commons.observe
import com.android.theta.databinding.ItemListFragmentBinding
import com.android.theta.user.model.Item
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ItemListFragment : Fragment() {

    private val viewModel by viewModels<ItemListViewModel>()
    private lateinit var binding: ItemListFragmentBinding

    private val itemListAdapter = ItemListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setItems()
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
                    Snackbar.make(binding.root, "Cart button clicked", Snackbar.LENGTH_LONG)
                        .show()
                    true
                }
                else -> super.onOptionsItemSelected(it)
            }
        }
    }

}