package com.android.theta.user

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.theta.MainActivityViewModel
import com.android.theta.commons.CustomRepeatListener
import com.android.theta.commons.observe
import com.android.theta.databinding.CartFragmentBinding
import com.android.theta.user.model.ItemCart
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : Fragment(), CustomRepeatListener {


    private lateinit var binding: CartFragmentBinding
    private val activityViewModel by activityViewModels<MainActivityViewModel>();


    private val cartAdapter = CartAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel.setItems()
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
        binding.floatingActionButton.setOnClickListener() {

            Snackbar.make(binding.root, "checkout", Snackbar.LENGTH_LONG)
                .show()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        hideButton()

        activityViewModel.apply {
            observe(cartValue, ::observeItems)
        }
    }

    private fun hideButton() {
        binding.floatingActionButton.hide()
    }

    private fun showButton() {
        binding.floatingActionButton.show()
    }


    private fun observeItems(list: List<ItemCart>?) {
        hideButton()
        if (list == null || list.isEmpty()) {
            return
        }
        showButton()
        cartAdapter.submitList(list)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onRepeat(cart: ItemCart) {
        Snackbar.make(binding.root, "Repeated item  ${cart.name}", Snackbar.LENGTH_LONG)
            .show()
        activityViewModel.repeatItem(cart)
        cartAdapter.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun clearItem(cart: ItemCart) {
        Snackbar.make(binding.root, "reduced /removed  item  ${cart.name}", Snackbar.LENGTH_LONG)
            .show()
        activityViewModel.clearItem(cart)
        if (activityViewModel.getItems()?.isEmpty()) {
            hideButton()
        }
        cartAdapter.notifyDataSetChanged()
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