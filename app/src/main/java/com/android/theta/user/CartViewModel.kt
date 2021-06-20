package com.android.theta.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.theta.user.model.ItemCart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CartViewModel : ViewModel() {

    val cartValue = MutableLiveData<List<ItemCart>>();

    /*fun setItems() {

        viewModelScope.launch {

            withContext(Dispatchers.IO)
            {
                cartValue.postValue(getItems())
            }
        }

    }*/

    /*fun getCartValues (): MutableLiveData<List<Item>>{

       return cartValue;

    }*/

   /* private fun getItems(): LinkedList<ItemCart> {

        var items = LinkedList<ItemCart>()
        items.add(
            ItemCart(
                name = "Chicken Italian Sandwich",
                price = "100",
                desc = "Chicken tikka, onion, capsicum, mayonnaise, lettuce.",
                imgSrc = "test",
                serveCount = "2",
                count = 1
            )
        )
        return items
    }
*/

}