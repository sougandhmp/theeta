package com.android.theta.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.theta.user.model.Hotel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val hotels = MutableLiveData<List<Hotel>>()


    private fun getHotelList(): List<Hotel> {
        var hotelList = ArrayList<Hotel>()
        hotelList.add(Hotel(id = 1, name = "Hotel1", styleName = "kerala", avgPriceForTwo = "200"))
        hotelList.add(Hotel(id = 2, name = "Hotel2", styleName = "south", avgPriceForTwo ="200"))
        hotelList.add(Hotel(id = 3, name = "Hotel3", styleName = "north",avgPriceForTwo = "100"))
        hotelList.add(Hotel(id = 4, name = "Hotel4", styleName = "south",avgPriceForTwo = "300"))
        hotelList.add(Hotel(id = 5, name = "Hotel5", styleName = "north", avgPriceForTwo ="600"))
        hotelList.add(Hotel(id = 6, name = "Hotel6", styleName = "south",avgPriceForTwo = "700"))
        return hotelList;

    }

    fun setHotelList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                hotels.postValue(getHotelList())
            }
        }
    }
}