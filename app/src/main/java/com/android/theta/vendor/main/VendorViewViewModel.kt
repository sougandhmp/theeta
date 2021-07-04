package com.android.theta.vendor.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.theta.vendor.model.VendorItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class VendorViewViewModel : ViewModel() {
    val vendorItemList = MutableLiveData<List<VendorItem>>()
    private var db = FirebaseFirestore.getInstance()
    fun setItems() {
        val email = FirebaseAuth.getInstance().currentUser?.email
        email?.let {
            db.collection("hotels").document(it).collection("items").get()
                .addOnSuccessListener { documentReference ->
                    val docs = documentReference.documents
                    val vendorList = arrayListOf<VendorItem>()
                    Timber.d("DocumentSnapshot added with ID: ${docs.size}")
                    for (doc in docs) {
                        doc.toObject(VendorItem::class.java)?.let { vendor ->
                            vendor.id = doc.id
                            vendorList.add(vendor)
                        }
                    }
                    vendorItemList.postValue(vendorList)
                }
                .addOnFailureListener { e -> Timber.w("Error adding document ${e.message}") }
        }
    }
}