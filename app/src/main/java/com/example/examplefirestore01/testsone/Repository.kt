package com.example.example01.testsone

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class Repository {
    private val COLLECTION_NAME = "prueba1"
    private val db = Firebase.firestore

    suspend fun insertData(data: Data) {
        db.collection(COLLECTION_NAME).document(data.numero!!.toString()).set(data).await()
    }

    // fetch data method using document id
    suspend fun fetchData(num: StateFlow<Int>): Data? {
        return db.collection(COLLECTION_NAME).document(num.toString()).get().await()
            .toObject(Data::class.java)
    }

    // fetch data variant using whereEqualTo()
    suspend fun fetchDataVariant(num: Int): Data? {
        val list = db.collection(COLLECTION_NAME).whereEqualTo("numero", num).get().await()
            .toObjects<Data>()
        return if (list.isEmpty()) {
            null
        } else {
            list[0]
        }
    }
}