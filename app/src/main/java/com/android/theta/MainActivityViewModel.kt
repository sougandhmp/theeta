package com.android.theta

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.theta.user.model.ItemCart

class MainActivityViewModel : ViewModel() {

    val cartValue = MutableLiveData<List<ItemCart>>();

    val itemList = arrayListOf<ItemCart>();

    fun setItems() {
        cartValue.postValue(itemList)


    }

    fun addItem() {
        itemList.add(
            ItemCart(
                name = "new added",
                price = "100",
                desc = "test.",
                imgSrc = "test",
                serveCount = "10",
                rating = 1
            )
        )
        cartValue.postValue(itemList)
    }


}