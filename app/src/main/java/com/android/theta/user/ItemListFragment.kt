package com.android.theta.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.theta.databinding.ItemListFragmentBinding


class ItemListFragment : Fragment() {

    companion object {
        fun newInstance() = ItemListFragment()
    }

    private lateinit var viewModel: IteamListModel
    private lateinit var binding: ItemListFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemListFragmentBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(IteamListModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IteamListModel::class.java)
        // TODO: Use the ViewModel
    }




}