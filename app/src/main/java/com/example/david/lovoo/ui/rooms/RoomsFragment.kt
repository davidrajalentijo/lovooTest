package com.example.david.lovoo.ui.rooms

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.david.lovoo.*
import com.example.david.lovoo.data.ViewModelFactory
import com.example.david.lovoo.models.Room
import com.example.david.lovoo.ui.OnActionsClickListener
import com.example.david.lovoo.ui.detail.DetailActivity

/*
    Fragment to show the rooms
 */
class RoomsFragment : Fragment(), OnActionsClickListener<Room> {

    private lateinit var viewModel: MainViewModel
    private lateinit var lv: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val rootView = inflater.inflate(R.layout.rooms_fragment, container, false)

        lv = rootView.findViewById(R.id.recyclerview_main_data_rooms) as RecyclerView

        lv.layoutManager = LinearLayoutManager(activity!!.applicationContext, RecyclerView.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity!!.applicationContext)).get(MainViewModel::class.java)
        viewModel.getRooms().observe(this, Observer<List<Room>> {
            val filter = it?.filter { it.lovooFact == null }
            lv.adapter = RoomsAdapter(filter!!, this)
        })

        return rootView
    }

    override fun onBookClicked(view: View, item: Room) {
        val bookRoomBottomDialogFragment = BookRoomFragmentDialog.newInstance(item.id)
        bookRoomBottomDialogFragment.show(activity!!.supportFragmentManager, "book_room_dialog_fragment")
    }

    override fun onRoomClicked(view: View, item: Room) {
        startActivity(Intent(activity, DetailActivity::class.java).putExtra("roomId", item.id))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_activity_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): RoomsFragment {
            val fragment = RoomsFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}