package com.android.theta.user.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.theta.user.model.Vendor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val vendors = MutableLiveData<List<Vendor>>()

    fun setHotelList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                vendors.postValue(getHotelList())
            }
        }
    }

    private fun getHotelList(): List<Vendor> {
        var vendors = ArrayList<Vendor>()
        vendors.add(
            Vendor(
                id = 1,
                name = "Hotel1",
                styleName = "kerala",
                avgPriceForTwo = "200",
                imgUrl = "@drawable/hotel1"
            )
        )
        vendors.add(
            Vendor(
                id = 2,
                name = "Hotel2",
                styleName = "south",
                avgPriceForTwo = "200",
                imgUrl = "@drawable/hotel1"
            )
        )
        vendors.add(
            Vendor(
                id = 3,
                name = "Hotel3",
                styleName = "north",
                avgPriceForTwo = "100",
                imgUrl = "@drawable/hotel1"
            )
        )
        vendors.add(
            Vendor(
                id = 4,
                name = "Hotel4",
                styleName = "south",
                avgPriceForTwo = "300",
                imgUrl = "@drawable/hotel1"
            )
        )
        vendors.add(
            Vendor(
                id = 5,
                name = "Hotel5",
                styleName = "north",
                avgPriceForTwo = "600",
                imgUrl = "@drawable/hotel1"
            )
        )
        vendors.add(
            Vendor(
                id = 6,
                name = "Hotel6",
                styleName = "south",
                avgPriceForTwo = "700",
                imgUrl = "@drawable/hotel1"
            )
        )
        return vendors;

    }


}