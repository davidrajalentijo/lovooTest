package com.example.david.lovoo.data

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.david.lovoo.BuildConfig
import com.example.david.lovoo.MainViewModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import com.example.david.lovoo.database.AppDatabase
import com.example.david.lovoo.database.RoomsDao
import okhttp3.OkHttpClient

/*
    ModelFactory to create retrofitConnection
 */
class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(createRoomsRepo(context)) as T
    }

    private fun createRoomsRepo(context: Context): RoomsRepo {

        return RoomsRepo(createWebService(), createExecutor(), createRoomsDao(context))
    }

    private fun createWebService(): WebService {
        val gson = GsonBuilder().create()

        val client = OkHttpClient.Builder()
                .addInterceptor(BasicAuthInterceptor(BuildConfig.Username, BuildConfig.Password))
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        return retrofit.create(WebService::class.java)
    }

    private fun createExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    private fun createRoomsDao(context: Context): RoomsDao {
        val d: AppDatabase = AppDatabase.getInstance(context)
        return d.roomsDao()
    }

    companion object {
        const val BASE_URL = "https://europe-west1-lv-trialwork.cloudfunctions.net/"

    }
}