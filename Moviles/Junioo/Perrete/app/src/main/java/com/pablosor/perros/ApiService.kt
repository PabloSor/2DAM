package com.pablosor.perros

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    // La interfaz se da el metodo por el cual consumimos de nuestra API
    @GET
    suspend fun getDogsByBreeds(@Url url:String):Response<PerreteResponse>
}