package com.android.theta.vendor.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.theta.vendor.model.VendorItem

class VendorViewViewModel : ViewModel() {
    val vendorView = MutableLiveData<List<VendorItem>>();

    private val vendorItemList = arrayListOf<VendorItem>();

    fun setItems() {

        for (i in 1..20) {

            var vendorItem = VendorItem(
                id = i,
                name = "test name $i",
                count = 1,
                serveCount = "2",
                imgSrc = "src",
                desc = "test item $i",
                price = "200",
                defaultStatus = i % 2==0

            )

            vendorItemList.add(
                vendorItem
            )
        }
        vendorView.postValue(vendorItemList)


    }
}