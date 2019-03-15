package com.example.david.lovoo.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.example.david.lovoo.MainViewModel
import com.example.david.lovoo.R
import com.example.david.lovoo.data.ViewModelFactory
import kotlinx.android.synthetic.main.detail_room.*

/*
    Activity that shows one room in detail
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_room)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(MainViewModel::class.java)

        val lv = findViewById<RecyclerView>(R.id.recyclerview_main_data_images)
        lv.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)

        val id = intent.extras.getString("roomId")

        viewModel.getRoomById(id).observe(this, Observer {
            if (it != null) {
                room_name_detail.text = it.name
                room_department_detail.text = it.department
                room_type_detail.text = it.type
                room_number_detail.text = it.roomNumber
                room_office_level_detail.text = it.officeLevel.toString()

                if (it.bookedTime != null && it.bookedLength != null) {
                    booked_group.visibility = View.VISIBLE
                    room_booked_hour.text = it.bookedTime
                    room_booked_length.text = it.bookedLength
                }

                if (it.lovooFact != null) {
                    lovoofact_group.visibility = View.VISIBLE
                    room_title.text = it.lovooFact!!.title
                    room_text.text = it.lovooFact!!.text
                    lv.adapter = ImageAdapter(it.lovooFact!!.images)
                }

            }
        })

    }
}