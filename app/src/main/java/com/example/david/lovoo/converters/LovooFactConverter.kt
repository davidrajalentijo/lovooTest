package com.example.david.lovoo.converters

import android.arch.persistence.room.TypeConverter
import com.example.david.lovoo.models.LovooFact
import com.google.gson.Gson

/*
      Convert LoovoFact to String and String to LovooFact
 */
class LovooFactConverter {

    @TypeConverter
    fun LovooFactToString(lovooFact: LovooFact?): String? = if (lovooFact == null) null else Gson().toJson(lovooFact)

    @TypeConverter
    fun stringToLovooFact(string: String?): LovooFact? = if (string == null) null else Gson().fromJson(string, LovooFact::class.java)
}