package com.example.coding_test_app.meetingsInfo

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coding_test_app.R
import com.example.coding_test_app.data.Meeting
import com.example.coding_test_app.data.Response
import com.example.coding_test_app.meetings.MeetingsActivity
import com.example.coding_test_app.meetings.MeetingsViewModel
import com.example.coding_test_app.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

class MeetingInfoActivity : AppCompatActivity() {
    private lateinit var viewModel: MeetingsInfoViewModel
    lateinit var titleTV: TextView
    lateinit var dateET: TextView
    lateinit var notesET: EditText
    lateinit var durationET: EditText
    lateinit var authorET: EditText
    lateinit var Save: Button

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meeting_info_page)

        viewModel = ViewModelProvider(this).get(MeetingsInfoViewModel::class.java)


        titleTV = findViewById(R.id.titleTV)
        dateET = findViewById(R.id.dateET)
        notesET = findViewById(R.id.notesET)
        durationET = findViewById(R.id.durationET)
        authorET = findViewById(R.id.authorET)
        Save = findViewById(R.id.save)


        val extras = intent.extras
        var title: String = ""
        var date: String = ""
        var notes: String = ""
        var duration: String = ""
        var author: String = ""

        if (extras != null) {
            title = extras.getString("Title").toString()
            date = extras.getString("Date").toString()
            notes = extras.getString("Notes").toString()
            duration = extras.getString("Duration").toString()
            author = extras.getString("Author").toString()
        }

        titleTV.text = title
        dateET.text = date
        notesET.setText(notes)
        durationET.setText(duration)
        authorET.setText(author)


        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                dateET.text = sdf.format(cal.time)
            }

        dateET.setOnClickListener {
            val mDatePicker = DatePickerDialog(
                this@MeetingInfoActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis())
            mDatePicker.show()
        }

        Save.setOnClickListener(View.OnClickListener {
            val meetingNotes = Meeting(titleTV.text.toString(),
                dateET.text.toString(),
                notesET.text.toString(),
                authorET.text.toString(),
                durationET.text.toString())
            setResponseUsingCallback(meetingNotes)
        })
    }

    private fun setResponseUsingCallback(meetingNotes : Meeting) {
        viewModel.setResponseUsingLiveData(meetingNotes).observe(this, {
            val intent = Intent(this@MeetingInfoActivity, MeetingsActivity::class.java)
            startActivity(intent)
        })
    }


}