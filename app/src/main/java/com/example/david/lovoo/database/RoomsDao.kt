package com.example.david.lovoo.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.david.lovoo.models.Room

@Dao
abstract class RoomsDao{
    @Query("SELECT * FROM rooms")
    abstract fun loadRooms(): LiveData<List<Room>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRooms(rooms: List<Room>)

    @Query("SELECT * FROM rooms Where lovooFact IS NOT NULL")
    abstract fun getRoomsLovooFact(): LiveData<List<Room>>

    @Query("SELECT * FROM rooms Where id=:id")
    abstract fun getRoomById(id: String): LiveData<Room>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateRoom(room: Room)
}