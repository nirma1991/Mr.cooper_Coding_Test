package com.example.coding_test_app.meetings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coding_test_app.data.FirebaseCallback
import com.example.coding_test_app.data.Response
import kotlinx.coroutines.Dispatchers

class MeetingsViewModel (
    private val repository: MeetingsRepository = MeetingsRepository()
): ViewModel() {
    fun getResponseUsingCallback(callback: FirebaseCallback) {
        repository.getResponseFromRealtimeDatabaseUsingCallback(callback)
    }

    fun getResponseUsingLiveData() : LiveData<Response> {
        return repository.getResponseFromRealtimeDatabaseUsingLiveData()
    }

    val responseLiveData = liveData(Dispatchers.IO) {
        emit(repository.getResponseFromRealtimeDatabaseUsingCoroutines())
    }
}