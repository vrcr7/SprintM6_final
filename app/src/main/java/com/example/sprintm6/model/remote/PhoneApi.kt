package com.example.sprintm6.model.remote

import com.example.sprintm6.model.remote.clases.DetailsPhoneApiClass
import com.example.sprintm6.model.remote.clases.PhoneApiClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneApi {


    // listado de telefonos
    @GET("products")
    suspend fun fecthPhoneList(): Response<List<PhoneApiClass>>


    // seleccionar uno

    @GET("details/{id}")
    suspend fun fechPhoneDetail(@Path("id") id: Int): Response<DetailsPhoneApiClass>
}






