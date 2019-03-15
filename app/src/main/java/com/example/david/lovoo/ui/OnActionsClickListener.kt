package com.example.david.lovoo.ui

import android.view.View
import com.example.david.lovoo.models.Room

//Interface to manage different actions in the RecyclerView
interface OnActionsClickListener<T : Room> {

    /**
     * The book order icon has been clicked
     */
    fun onBookClicked(view: View, item: T)

    /**
     * The Room has been clicked
     */
    fun onRoomClicked(view: View, item: T)

}