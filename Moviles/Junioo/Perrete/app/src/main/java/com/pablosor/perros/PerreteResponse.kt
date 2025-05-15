package com.pablosor.perros

import com.google.gson.annotations.SerializedName

data class PerreteResponse (
    @SerializedName("status") var estado: String,
    @SerializedName("message") var imagenes: List<String>
)