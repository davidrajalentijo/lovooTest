package com.example.david.lovoo.data

import android.arch.lifecycle.LiveData
import com.example.david.lovoo.database.RoomsDao
import com.example.david.lovoo.models.Room
import java.io.IOException
import java.util.concurrent.Executor

/**
 * Repository (repository pattern) to access events content. Events is maintained offline. EventsRepository is responsible from providing data to the upper layer
 * so it's not aware of where that data is coming from, local db or network.
 */
class RoomsRepo(private val webservice: WebService, private val executor: Executor, private val roomsDao: RoomsDao) {

    /*Get Rooms from Retrofit*/
    fun getRooms(): LiveData<List<Room>> {
        val data = roomsDao.loadRooms()
        executor.execute {
            try {
                val response = webservice.getRooms().execute()
                if (response.isSuccessful && response.body() != null) {
                    roomsDao.insertRooms(response.body()!!)
                } else {
                    //TODO return error to the upper layer somehow
                }
            } catch (e: IOException) {
                //TODO return error to the upper layer somehow
            }
        }
        return data
    }

    /*Get Rooms with LovooFact*/
    fun getRoomsLovooFact(): LiveData<List<Room>> {
        return roomsDao.getRoomsLovooFact()
    }

    /*Get Room by Id*/
    fun getRoomById(id: String): LiveData<Room> {
        return roomsDao.getRoomById(id)
    }

    /*Update a room*/
    fun updateRoom(room: Room) {
        executor.execute {
            try {
                roomsDao.updateRoom(room)
            } catch (e: IOException) {
                //TODO return error to the upper layer somehow
            }
        }
    }
}