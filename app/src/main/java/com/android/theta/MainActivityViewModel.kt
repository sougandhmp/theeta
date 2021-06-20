package com.android.theta

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.theta.user.model.Item
import com.android.theta.user.model.ItemCart

class MainActivityViewModel : ViewModel() {

    val cartValue = MutableLiveData<List<ItemCart>>();

    val itemList = arrayListOf<ItemCart>();

    fun setItems() {
        cartValue.postValue(itemList)


    }

    fun addItem(cart: Item) {
        var cartItem = ItemCart(
            name = cart.name,
            rating = cart.rating,
            serveCount = cart.serveCount,
            imgSrc = cart.desc,
            desc = cart.desc,
            price = cart.price
        )

        itemList.add(
            cartItem
        )
        cartValue.postValue(itemList)
    }


}