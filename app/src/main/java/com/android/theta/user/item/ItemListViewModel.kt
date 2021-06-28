package com.android.theta.user.item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.theta.user.model.Item
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class ItemListViewModel : ViewModel() {

    val itemList = MutableLiveData<List<Item>>()
    var db = FirebaseFirestore.getInstance()
    fun setItems(id: String) {
        db.collection("hotels").document(id).collection("items").get()
            .addOnSuccessListener { documentReference ->
                val docs = documentReference.documents
                val vendorList = arrayListOf<Item>()
                Timber.d("DocumentSnapshot added with ID: ${docs.size}")
                for (doc in docs) {
                    val vendor = Item(
                        id = doc.id,
                        name = doc["name"] as String,
                        price = doc["price"] as Long,
                        imgSrc = doc["image"] as String,
                        rating = doc["rating"] as Long
                    )
                    vendorList.add(vendor)
                }
                itemList.postValue(vendorList)
            }
            .addOnFailureListener { e -> Timber.w("Error adding document ${e.message}") }

    }
}