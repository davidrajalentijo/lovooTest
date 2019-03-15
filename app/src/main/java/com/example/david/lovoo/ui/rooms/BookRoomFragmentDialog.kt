package com.example.david.lovoo.ui.rooms

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.david.lovoo.MainViewModel
import com.example.david.lovoo.R
import com.example.david.lovoo.data.ViewModelFactory
import com.example.david.lovoo.models.Room
import kotlinx.android.synthetic.main.book_room.*

/*
      Dialog fragment for book a room
 */
class BookRoomFragmentDialog : BottomSheetDialogFragment() {

    private lateinit var roomId: String

    private lateinit var viewModel: MainViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            roomId = it!!.getString(ARG_ITEM_ID)
        }
        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity!!.applicationContext)).get(MainViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.book_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgClose.setOnClickListener { dismiss() }
        viewModel.getRoomById(roomId).observe(this, Observer {
            length_input.text = Editable.Factory.getInstance().newEditable(it?.bookedLength.orEmpty())
            time_input.text = Editable.Factory.getInstance().newEditable(it?.bookedTime.orEmpty())
            var updatedRoom: Room? = it
            book_button.setOnClickListener {
                if (time_input.text.isNotEmpty() && length_input.text.isNotEmpty()) {
                    updatedRoom?.bookedTime = time_input.text.toString()
                    updatedRoom?.bookedLength = length_input.text.toString()
                    updatedRoom?.let { it1 -> viewModel.updateRoom(it1) }
                    dismiss()
                }
            }
        })

    }

    companion object {

        const val ARG_ITEM_ID = "roomID"

        @JvmStatic
        fun newInstance(roomId: String): BookRoomFragmentDialog {
            return BookRoomFragmentDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_ITEM_ID, roomId)
                }
            }
        }
    }
}