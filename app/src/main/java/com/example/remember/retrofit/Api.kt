package com.example.remember.retrofit

import com.example.remember.model.Customer
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("customers")
    fun getCustomer(): Call<List<Customer>>
}