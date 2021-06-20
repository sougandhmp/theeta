package com.android.theta.commons

import android.view.View
import com.android.theta.user.model.Item
import com.android.theta.user.model.ItemCart

interface CustomRepeatListener {

    fun  onRepeat(cart: ItemCart)

}