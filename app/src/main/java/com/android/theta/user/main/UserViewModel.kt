package com.android.theta.user.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.theta.user.model.Vendor
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber


class UserViewModel : ViewModel() {
    val vendors = MutableLiveData<List<Vendor>>()
    var db = FirebaseFirestore.getInstance()
    fun setHotelList() {
        db.collection("hotels").get()
            .addOnSuccessListener { documentReference ->
                val docs = documentReference.documents
                val vendorList = arrayListOf<Vendor>()
                Timber.d("DocumentSnapshot added with ID: ${docs.size}")
                for (doc in docs) {
                    val vendor = Vendor(
                        id = doc.id,
                        name = doc["name"] as String,
                        styleName = doc["address"] as String,
                        contactNo = doc["contact_no"] as String,
                        imgUrl = doc["img_url"] as String
                    )
                    vendorList.add(vendor)
                }
                vendors.postValue(vendorList)
            }
            .addOnFailureListener { e -> Timber.w("Error adding document ${e.message}") }
    }

}