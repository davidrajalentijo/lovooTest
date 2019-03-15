package com.example.david.lovoo.models

import android.arch.persistence.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "rooms")
class Room(
        @ColumnInfo(name = "department")
        @SerializedName("department")
        @Expose
        var department: String,

        @ColumnInfo(name = "name")
        @SerializedName("name")
        @Expose
        var name: String,

        @ColumnInfo(name = "officeLevel")
        @SerializedName("officeLevel")
        @Expose
        var officeLevel: Int,

        @ColumnInfo(name = "type")
        @SerializedName("type")
        @Expose
        var type: String?,

        @ColumnInfo(name = "roomNumber")
        @SerializedName("roomNumber")
        @Expose
        var roomNumber: String,

        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        @Expose
        var id: String,

        @ColumnInfo(name = "lovooFact")
        @SerializedName("lovooFact")
        @Expose
        var lovooFact: LovooFact?,

        @ColumnInfo(name = "bookedTime")
        var bookedTime: String?,

        @ColumnInfo(name = "bookedLength")
        var bookedLength: String?

)



