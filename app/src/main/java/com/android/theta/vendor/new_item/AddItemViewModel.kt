package com.android.theta.vendor.new_item

import androidx.lifecycle.ViewModel
import com.android.theta.vendor.model.VendorItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class AddItemViewModel : ViewModel() {
    private var db = FirebaseFirestore.getInstance()
    fun addToDatabase(item: VendorItem) {
        val email = FirebaseAuth.getInstance().currentUser?.email
        email?.let {
            db.collection("hotels").document(it).collection("items").add(item)
                .addOnSuccessListener { documentReference ->
                    Timber.e("added : ${documentReference.id}")
                }.addOnFailureListener { error ->
                Timber.e(error)
            }
        }
    }
}