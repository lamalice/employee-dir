package com.example.employeedirectory.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeedirectory.data.*
import com.example.employeedirectory.utils.DataState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityViewModel() : ViewModel() {
    private var viewAdapter: MainActivityAdapter? = null

    private val _dataState = MutableLiveData<DataState<String>>()
    val dataState: LiveData<DataState<String>>
        get() = _dataState

    fun fetchData() {
        _dataState.value = DataState.Loading
           val api = RetrofitBuilder.getRetrofit()

            api.getEmployees().enqueue(object : Callback<EmployeeModel?> {
                override fun onResponse(
                    call: Call<EmployeeModel?>,
                    response: Response<EmployeeModel?>) {
                    if (response.isSuccessful){
                            response.body()?.let {
                                if (it.getEmployeesList() != null) {
                                    val employeesList: List<Employees> = it.getEmployeesList()
                                    for (employee in employeesList) {
                                        Log.i("CHECK_RESPONSE", "Employee: ${employee.getFullName()}")
                                    }
                                }
                            }
                        _dataState.value = DataState.Success("Success")
                        }
                    else {
                        _dataState.value = DataState.Error("Error Fetching Data")
                        Log.i("CHECK_RESPONSE", "Response not successful: ${response.code()}")
                    }
                }
                override fun onFailure(call: Call<EmployeeModel?>, t: Throwable) {
                    _dataState.value = DataState.Error("Error Fetching Data")
                    Log.i("CHECK_RESPONSE", "Request failed: ${t.message}")
                }
            })
        }
}