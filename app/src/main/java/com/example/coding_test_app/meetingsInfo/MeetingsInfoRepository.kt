package com.example.coding_test_app.meetingsInfo

import androidx.lifecycle.MutableLiveData
import com.example.coding_test_app.utils.Constants.MEETINGS_REF
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MeetingsInfoRepository(
    private val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference,
    private val meetingRef: DatabaseReference = rootRef.child(MEETINGS_REF)
) {
    fun getResponseFromRealtimeDatabaseUsingLiveData(title : String,date: String,notes : String,author : String,duration : String, meetingName: String): MutableLiveData<String> {
        var mutableLiveData = MutableLiveData<String>()

        val meetingInfo: HashMap<String, Any> = HashMap()
        meetingInfo["notes"] = notes
        meetingInfo["date"] = date
        meetingInfo["duration"] = duration
        meetingInfo["author"] = author

        meetingRef.child(meetingName).updateChildren(meetingInfo)
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