package com.example.david.lovoo.ui.rooms

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.david.lovoo.MainViewModel
import com.example.david.lovoo.ui.OnActionsClickListener
import com.example.david.lovoo.R
import com.example.david.lovoo.data.ViewModelFactory
import com.example.david.lovoo.models.Room
import com.example.david.lovoo.ui.detail.DetailActivity

/*
    Fragment for show the rooms that contains LovooFact
 */
class RoomsFactFragment : Fragment(), OnActionsClickListener<Room> {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.roomsfact_fragment, container, false)

        val lv = rootView.findViewById(R.id.recyclerview_main_data_roomsfact) as RecyclerView
        lv.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayout.VERTICAL, false)

        lv.layoutManager = LinearLayoutManager(activity!!.applicationContext, RecyclerView.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity!!.applicationContext)).get(MainViewModel::class.java)
        viewModel.getRoomsLovooFact().observe(this, Observer<List<Room>> {
            lv.adapter = RoomsAdapter(it!!, this)
        })

        return rootView
    }

    override fun onBookClicked(view: View, item: Room) {
        //For the moment FactRooms can't be booked
    }

    override fun onRoomClicked(view: View, item: Room) {
        startActivity(Intent(activity, DetailActivity::class.java).putExtra("roomId", item.id))
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): RoomsFactFragment {
            val fragment = RoomsFactFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}