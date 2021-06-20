package com.android.theta.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.theta.commons.observe
import com.android.theta.databinding.CartFragmentBinding
import com.android.theta.user.model.ItemCart
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : Fragment() {


    private val viewModel by viewModels<CartViewModel>()
    private lateinit var binding: CartFragmentBinding

    private val cartAdapter = CartAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setItems()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CartFragmentBinding.inflate(layoutInflater, container, false).apply {
            vendorListRecycler.apply {
                adapter = cartAdapter
            }
            lifecycleOwner = this@CartFragment
        }
        // handleAppBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.apply {
            observe(cartValue, ::observeItems)
        }
    }

    private fun observeItems(list: List<ItemCart>?) {
        list ?: return
        cartAdapter.submitList(list)
    }

    /* private fun handleAppBar(){
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
     }*/
}