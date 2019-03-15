package com.example.david.lovoo.data

import com.example.david.lovoo.models.Room
import retrofit2.Call
import retrofit2.http.GET

/*
    Different calls to Lovoo API
 */
interface WebService {

    @GET("lovooOffice")
    fun getRooms(): Call<List<Room>>

}