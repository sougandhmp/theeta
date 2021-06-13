package com.android.theta.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.theta.user.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ItemListViewModel : ViewModel() {

    val itemList=MutableLiveData<List<Item>>();

    fun setItems(){

        viewModelScope.launch {

            withContext(Dispatchers.IO)
            {
                itemList.postValue(getItems())
            }
        }

    }

    private fun getItems():List<Item>
    {

      var items = LinkedList<Item>()
        items.add(Item(name = "Chicken Italian Sandwich",price = "100",desc = "Chicken tikka, onion, capsicum, mayonnaise, lettuce.", imgSrc = "test",serveCount = "2"))
        items.add(Item(name = "Chicken Bucket Meal [Regular]",price = "400",desc = "8 pieces Fried Chicken + French Fries + Potato Wedges + 2 Coke [250 ml]", imgSrc = "test",serveCount = "1"))
        items.add(Item(name = "Non Veg Meal for 2",price = "400",desc = "Chicken tikka, onion, capsicum, mayonnaise, lettuce", imgSrc = "test",serveCount = "4"))
        items.add(Item(name = "Italian Sandwich",price = "600",desc = "Chicken tikka, onion, capsicum, mayonnaise, lettuce", imgSrc = "test",serveCount = "9"))
        items.add(Item(name = "Bucket Meal",price = "800",desc = "Chicken tikka, onion, capsicum, mayonnaise, lettuce", imgSrc = "test",serveCount = "6"))
        return items
    }




}