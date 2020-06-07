package com.example.remember.retrofit

import com.example.remember.model.Customer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("customers")
    fun getCustomer(): Call<List<Customer>>

    @POST("saveCustomer")
    fun saveCustomer(@Body customer: Customer): Call<Customer>
}