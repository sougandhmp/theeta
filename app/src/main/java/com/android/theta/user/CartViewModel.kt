package com.android.theta.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.theta.user.model.Cart
import com.android.theta.user.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CartViewModel : ViewModel() {

     val cartValue=MutableLiveData<List<Item>>();

    fun setItems(){

        viewModelScope.launch {

            withContext(Dispatchers.IO)
            {
                cartValue.postValue(getItems())
            }
        }

    }

    /*fun getCartValues (): MutableLiveData<List<Item>>{

       return cartValue;

    }*/

    private fun getItems(): LinkedList<Item> {

        var items = LinkedList<Item>()
        items.add(Item(name = "Chicken Italian Sandwich",price = "100",desc = "Chicken tikka, onion, capsicum, mayonnaise, lettuce.", imgSrc = "test",serveCount = "2",rating = 1))
        return items
    }




}