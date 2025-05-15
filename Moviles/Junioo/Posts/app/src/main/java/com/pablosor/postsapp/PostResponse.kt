package com.pablosor.postsapp

import com.google.gson.annotations.SerializedName

data class PostResponse (
    @SerializedName("title") var titulo: String,
    @SerializedName("body") var contenido: String
)