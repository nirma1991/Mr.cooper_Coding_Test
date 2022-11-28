package com.example.coding_test_app.meetings

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coding_test_app.R
import com.example.coding_test_app.adapter.MeetingAdapter
import com.example.coding_test_app.data.FirebaseCallback
import com.example.coding_test_app.data.Meeting
import com.example.coding_test_app.data.Response
import com.example.coding_test_app.utils.Constants.TAG


class MeetingsActivity : AppCompatActivity() {
    private lateinit var viewModel: MeetingsViewModel
    lateinit var meetingModelArrayList :  ArrayList<Meeting>
    lateinit var meetingRV : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meeting_page)
        viewModel = ViewModelProvider(this).get(MeetingsViewModel::class.java)

        meetingRV = findViewById<RecyclerView>(R.id.idRVMeeting)
        meetingModelArrayList = ArrayList<Meeting>()

        //First Approach
        getResponseUsingCallback()
        //Second Approach
        getResponseUsingLiveData()
        //Third Approach
        getResponseUsingCoroutines()
    }

    private fun getResponseUsingCallback() {
        viewModel.getResponseUsingCallback(object : FirebaseCallback {
            override fun onResponse(it: Response) {
                it.meetings?.let { meetings ->
                    meetings.forEach{ meeting ->
                        meeting.title?.let {
                            meetingModelArrayList.add(Meeting(meeting.title, meeting.date, meeting.notes, meeting.author, meeting.duration))
                            val meetingAdapter = MeetingAdapter(this@MeetingsActivity, meetingModelArrayList)
                            val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                            meetingRV.layoutManager = linearLayoutManager
                            meetingRV.adapter = meetingAdapter
                            //Log.i(TAG, it)
                        }
                    }
                }
                print(it)
            }
        })
    }

    private fun getResponseUsingLiveData() {
        viewModel.getResponseUsingLiveData().observe(this, {
            print(it)
        })
    }

    private fun getResponseUsingCoroutines() {
        viewModel.responseLiveData.observe(this, {

        })
    }

    private fun print(response: Response) {
        response.meetings?.let { meetings ->
            meetings.forEach{ product ->
                product.title?.let {
                    Log.i(TAG, it)
                }
            }
        }

        response.exception?.let { exception ->
            exception.message?.let {
                Log.e(TAG, it)
            }
        }
    }
}
