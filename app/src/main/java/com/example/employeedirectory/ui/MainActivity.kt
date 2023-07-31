package com.example.employeedirectory.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeedirectory.data.Employees
import com.example.employeedirectory.databinding.ActivityMainBinding
import com.example.employeedirectory.utils.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private var viewAdapter: MainActivityAdapter? = null
    private var employeesList: ArrayList<Employees>? = null
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainScope.launch{
            setUpObservers()
        }
    }

    private fun setUpObservers(){
        viewModel.dataState.observe(this, Observer {
            it.let {state ->
                when(state) {
                    is DataState.Success -> {
                        val data = state.data
                    }
                    is DataState.Loading -> {
                        val loading = "loading"
                    }
                    is DataState.Error -> {
                        val errorMessage = state.message
                    }
                    else -> {}
                }
            }
        })
        viewModel.fetchData()
    }

    private fun setUpMainActivityAdapter(){
        binding.employeeRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewAdapter = MainActivityAdapter(employeesList!!)
        binding.employeeRecyclerView.adapter = viewAdapter
    }
}