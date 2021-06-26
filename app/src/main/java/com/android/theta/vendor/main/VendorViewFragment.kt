package com.android.theta.vendor.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.theta.R
import com.android.theta.commons.observe
import com.android.theta.databinding.VendorViewFragmentBinding
import com.android.theta.vendor.model.VendorItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VendorViewFragment : Fragment() {


    private val vendorViewModel by viewModels<VendorViewViewModel>()
    private lateinit var binding: VendorViewFragmentBinding

    private var vendorViewAdapter = VendorViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vendorViewModel.setItems()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = VendorViewFragmentBinding.inflate(layoutInflater, container, false).apply {
            vendorListRecycler.apply {
                adapter = vendorViewAdapter
            }
            lifecycleOwner = this@VendorViewFragment
        }
        var thiscontext = context
        context?.let { onlineSwitch(it) }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        vendorViewModel.apply {
            observe(vendorView, ::observeItems)
        }
    }

    private fun observeItems(list: List<VendorItem>?) {
        list ?: return
        vendorViewAdapter.submitList(list)
    }


    private fun onlineSwitch(context: Context) {
        binding.switchButton.setOnCheckedChangeListener { buttonView, isChecked ->
            run {
                if (isChecked)
                    dialog(
                        context,
                        buttonView,
                        getString(R.string.warning),
                        "You really want  to go online?",
                        getString(R.string.store_online)
                    )

                 else
                    dialog(
                        context,
                        buttonView,
                        getString(R.string.warning),
                        "You really want  to go offline ?",
                        getString(R.string.store_offline)
                    )

            }
        }
    }

    private fun dialog(
        context: Context,
        buttonView: CompoundButton,
        title: String,
        message: String,
        text: String
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setNeutralButton("cancel") { dialog, which ->
                // Respond to neutral button press
            }
            .setNegativeButton("decline") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("accept") { dialog, which ->
                buttonView.text = text
            }
            .show()
    }

}