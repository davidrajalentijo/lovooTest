package com.example.david.lovoo.ui.rooms

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.david.lovoo.ui.OnActionsClickListener
import com.example.david.lovoo.R
import com.example.david.lovoo.models.Room
import kotlinx.android.synthetic.main.list_rooms.view.*

/*
    Adapter for the list of rooms
 */
class RoomsAdapter(val rooms: List<Room>, private val actionsListener: OnActionsClickListener<Room>) : RecyclerView.Adapter<RoomsAdapter.RoomsAdapterViewHolder>() {

    override fun getItemCount(): Int {
        return rooms.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_rooms, parent, false)
        return RoomsAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomsAdapterViewHolder, position: Int) {
        holder?.bindRoom(position)
    }

    inner class RoomsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindRoom(position: Int) {
            val room = rooms[position]
            itemView.apply {
                name_room.text = room.name
                department_room.text = room.department
                type_room.text = if (room.type != null) room.type else resources.getString(R.string.no_type)
                viewBackground.setOnClickListener { actionsListener.onRoomClicked(itemView, room) }
                if (room.type == resources.getString(R.string.meeting_type)) {
                    hide_room.visibility = View.VISIBLE
                    hide_room.setOnClickListener { actionsListener.onBookClicked(itemView, room) }
                }
            }
        }
    }

}