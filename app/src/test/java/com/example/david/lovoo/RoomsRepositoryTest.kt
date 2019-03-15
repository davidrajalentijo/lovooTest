package com.example.david.lovoo

import com.example.david.lovoo.data.RoomsRepo
import com.example.david.lovoo.data.WebService
import com.example.david.lovoo.database.RoomsDao
import com.example.david.lovoo.models.Room
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.concurrent.Executor

class RoomsRepositoryTest {

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    private lateinit var roomsDao: RoomsDao
    private lateinit var api: WebService

    private lateinit var roomsRepository: RoomsRepo

    @Before
    fun setup() {
        roomsDao = Mockito.mock(RoomsDao::class.java)
        api = Mockito.mock(WebService::class.java)

        val executor = Executor { command -> command.run() }

        roomsRepository = RoomsRepo(api, executor, roomsDao)

    }

    @Test
    fun `roomsDao-getRoomById is called`() {
        roomsRepository.getRoomById("id")
        Mockito.verify(roomsDao).getRoomById("id")
    }

    @Test
    fun `roomsDao-getRoomsLovooFact is called`() {
        roomsRepository.getRoomsLovooFact()
        Mockito.verify(roomsDao).getRoomsLovooFact()
    }

    @Test
    fun `roomsDao-updateRoom is called`() {
        val room = Room("", "", 1, "", "", "id", null, null, null)
        roomsRepository.updateRoom(room)
        Mockito.verify(roomsDao).updateRoom(room)
    }

}