package com.example.coding_test_app.meetingsInfo

import androidx.lifecycle.MutableLiveData
import com.example.coding_test_app.data.FirebaseCallback
import com.example.coding_test_app.data.Meeting
import com.example.coding_test_app.data.Response
import com.example.coding_test_app.utils.Constants.MEETINGS_REF
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await


class MeetingsInfoRepository(
    private val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference,
    private val meetingRef: DatabaseReference = rootRef.child(MEETINGS_REF)
) {
    fun getResponseFromRealtimeDatabaseUsingLiveData(meeting: Meeting): MutableLiveData<String> {
        var mutableLiveData = MutableLiveData<String>()

        meetingRef.child("Meeting1").child("notes").setValue(meeting.notes)
        meetingRef.child("Meeting1").child("date").setValue(meeting.date)
        meetingRef.child("Meeting1").child("duration").setValue(meeting.duration)
        meetingRef.child("Meeting1").child("author").setValue(meeting.author)
            .addOnCompleteListener { task ->
                mutableLiveData.value = task.toString()
            }
        return mutableLiveData
    }

    /*fun getResponseFromRealtimeDatabaseUsingLiveData() : MutableLiveData<Response> {
        val mutableLiveData = MutableLiveData<Response>()
        meetingRef.get().addOnCompleteListener { task ->
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
            response.meetings = meetingRef.get().await().children.map { snapShot ->
                snapShot.getValue(Meeting::class.java)!!
            }
        } catch (exception: Exception) {
            response.exception = exception
        }
        return response
    }*/
}