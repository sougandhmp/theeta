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
        hotelList.add(Hotel(name = "Hotel1", "kerala", "200"))
        hotelList.add(Hotel(name = "Hotel2", "south", "200"))
        hotelList.add(Hotel(name = "Hotel3", "north", "100"))
        hotelList.add(Hotel(name = "Hotel4", "south", "300"))
        hotelList.add(Hotel(name = "Hotel5", "north", "600"))
        hotelList.add(Hotel(name = "Hotel6", "south", "700"))
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