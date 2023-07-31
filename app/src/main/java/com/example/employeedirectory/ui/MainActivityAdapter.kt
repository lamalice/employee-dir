package com.example.employeedirectory.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedirectory.data.EmployeeModel
import com.example.employeedirectory.data.Employees
import com.example.employeedirectory.databinding.SingleEmployeeCardBinding

class MainActivityAdapter(private val employees: List<Employees>) :
    RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    var onEmployeeClick: ((EmployeeModel) -> Unit)? = null

    class ViewHolder(binding: SingleEmployeeCardBinding) : RecyclerView.ViewHolder(binding.root) {
        var employeeViewCard = binding.employeeViewCard
        var name = binding.employeeName
        var positionTitle = binding.employeePosition
        var photo = binding.employeeImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SingleEmployeeCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model : Employees = employees[position]
        holder.name.text = model.getFullName()
    }

}