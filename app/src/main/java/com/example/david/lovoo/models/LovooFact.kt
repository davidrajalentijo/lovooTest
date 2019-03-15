package com.example.david.lovoo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LovooFact(

        @SerializedName("title")
        @Expose
        var title: String,

        @SerializedName("images")
        @Expose
        var images: List<String>,

        @SerializedName("text")
        @Expose
        var text: String

)