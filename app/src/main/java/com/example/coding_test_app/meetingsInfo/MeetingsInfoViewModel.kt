package com.example.coding_test_app.meetingsInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coding_test_app.data.FirebaseCallback
import com.example.coding_test_app.data.Meeting
import com.example.coding_test_app.data.Response
import com.example.coding_test_app.meetings.MeetingsRepository
import kotlinx.coroutines.Dispatchers

class MeetingsInfoViewModel (
    private val repository: MeetingsInfoRepository = MeetingsInfoRepository()
): ViewModel() {
    fun setResponseUsingLiveData(meeting: Meeting) : LiveData<String>{
        return repository.getResponseFromRealtimeDatabaseUsingLiveData(meeting)
    }

    // Different Approches

    /*fun setResponseUsingLiveData() : LiveData<Response> {
        return repository.getResponseFromRealtimeDatabaseUsingLiveData()
    }

    val responseLiveData = liveData(Dispatchers.IO) {
        emit(repository.getResponseFromRealtimeDatabaseUsingCoroutines())
    }*/
}