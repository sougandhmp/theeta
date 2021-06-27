package com.android.theta

import android.os.Build
import androidx.annotation.RequiresApi
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

    fun getItems():List<ItemCart> {
       return  itemList;


    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun addItem(cart: Item) {

       val optional= itemList.stream().filter {
            it.id == cart.id
        }.findFirst();
        if(optional.isPresent)
        {
           val itemCart= optional.get();
            itemCart.count =++itemCart.count
            return

        }


        val cartItem = ItemCart(
            id=cart.id,
            name = cart.name,
            count = 1,
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

    @RequiresApi(Build.VERSION_CODES.N)
    fun repeatItem(cart: ItemCart) {

        val optional = itemList.stream().filter {
            it.id == cart.id
        }.findFirst();
        if (optional.isPresent) {
            val itemCart = optional.get();
            itemCart.count = ++itemCart.count
            return

        }
        itemList.add(
            cart
        )
        cartValue.postValue(itemList)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clearItem(cart: ItemCart) {

        val optional = itemList.stream().filter {
            it.id == cart.id
        }.findFirst();
        if (optional.isPresent) {
            val itemCart = optional.get();
          if(itemCart.count >1)
          {
              itemCart.count =--itemCart.count
              return
          }

            itemList.remove(itemCart)
        }

        cartValue.postValue(itemList)
    }


}