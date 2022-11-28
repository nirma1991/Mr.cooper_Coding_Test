package com.example.coding_test_app.meetings

import androidx.lifecycle.MutableLiveData
import com.example.coding_test_app.data.FirebaseCallback
import com.example.coding_test_app.data.Meeting
import com.example.coding_test_app.data.Response
import com.example.coding_test_app.utils.Constants.MEETINGS_REF
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await


class MeetingsRepository(
    private val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference,
    private val productRef: DatabaseReference = rootRef.child(MEETINGS_REF)
) {
    fun getResponseFromRealtimeDatabaseUsingCallback(callback: FirebaseCallback) {
        productRef.get().addOnCompleteListener { task ->
            val response = Response()
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    response.meetings = result.children.map { snapShot ->
                        snapShot.getValue(Meeting::class.java)!!
                    }
                }
            } else {
                response.exception = task.exception
            }
            callback.onResponse(response)
        }
    }

    fun getResponseFromRealtimeDatabaseUsingLiveData() : MutableLiveData<Response> {
        val mutableLiveData = MutableLiveData<Response>()
        productRef.get().addOnCompleteListener { task ->
            val response = Response()
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    response.meetings = result.children.map { snapShot ->
                        snapShot.getValue(Meeting::class.java)!!
                    }
                }
            } else {
                response.exception = task.exception
            }
            mutableLiveData.value = response
        }
        return mutableLiveData
    }

    suspend fun getResponseFromRealtimeDatabaseUsingCoroutines(): Response {
        val response = Response()
        try {
            response.meetings = productRef.get().await().children.map { snapShot ->
                snapShot.getValue(Meeting::class.java)!!
            }
        } catch (exception: Exception) {
            response.exception = exception
        }
        return response
    }
}