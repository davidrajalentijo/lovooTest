package com.example.david.lovoo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.david.lovoo.data.RoomsRepo
import com.example.david.lovoo.models.Room

/*
    ViewModel to manage the call between the view and the repository
 */
class MainViewModel(private val eventsRepository: RoomsRepo) : ViewModel() {

    fun getRooms(): LiveData<List<Room>> {
        return eventsRepository.getRooms()
    }

    fun getRoomsLovooFact(): LiveData<List<Room>> {
        return eventsRepository.getRoomsLovooFact()
    }

    fun getRoomById(id: String): LiveData<Room> {
        return eventsRepository.getRoomById(id)
    }

    fun updateRoom(room: Room) {
        eventsRepository.updateRoom(room)
    }

}