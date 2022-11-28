package com.example.coding_test_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coding_test_app.R
import com.example.coding_test_app.data.Meeting
import com.example.coding_test_app.meetingsInfo.MeetingInfoActivity
import java.io.Serializable

class MeetingAdapter(private val context: Context, meetingModelArrayList: ArrayList<Meeting>) :
    RecyclerView.Adapter<MeetingAdapter.ViewHolder>() {
    private val meetingModelArrayList: ArrayList<Meeting>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingAdapter.ViewHolder {
        // to inflate the layout for each item of recycler view.
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeetingAdapter.ViewHolder, position: Int) {
        // to set data to textview and imageview of each card layout
        val model: Meeting = meetingModelArrayList[position]
        holder.meetingNameTV.setText(model.title)
        holder.meetingNameTV.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, MeetingInfoActivity::class.java)
            intent.putExtra("Title", model.title)
            intent.putExtra("Date", model.date)
            intent.putExtra("Notes", model.notes)
            intent.putExtra("Duration", model.duration)
            intent.putExtra("Author", model.author)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        })

    }

    override fun getItemCount(): Int {
        // this method is used for showing number of card items in recycler view.
        return meetingModelArrayList.size
    }

    // View holder class for initializing of your views such as TextView and Imageview.
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val meetingNameTV: TextView
        init {
            meetingNameTV = itemView.findViewById(R.id.idTVMeetingName)
        }
    }

    // Constructor
    init {
        this.meetingModelArrayList = meetingModelArrayList
    }
}
