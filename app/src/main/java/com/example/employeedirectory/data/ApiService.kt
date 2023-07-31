package com.example.employeedirectory.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("sq-mobile-interview/employees.json")
    fun getEmployees(): Call<EmployeeModel>
}