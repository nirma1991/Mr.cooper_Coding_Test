package com.example.coding_test_app.meetingsInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coding_test_app.data.Meeting

class MeetingsInfoViewModel (
    private val repository: MeetingsInfoRepository = MeetingsInfoRepository()
): ViewModel() {
    fun setResponseUsingLiveData(title : String,date: String,notes : String,author : String,duration : String,meetingName: String) : LiveData<String>{
        return repository.getResponseFromRealtimeDatabaseUsingLiveData(title, date, notes, author, duration, meetingName)
    }

    // Different Approches

    /*fun setResponseUsingLiveData() : LiveData<Response> {
        return repository.getResponseFromRealtimeDatabaseUsingLiveData()
    }

    val responseLiveData = liveData(Dispatchers.IO) {
        emit(repository.getResponseFromRealtimeDatabaseUsingCoroutines())
    }*/
}