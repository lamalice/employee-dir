package com.example.employeedirectory.data


class EmployeeModel (
    private var employees: List<Employees>
) {
    fun getEmployeesList(): List<Employees> {
        return employees
    }
}

class Employees(
    private var uuid: String,
    private var full_name: String,
    private var phone_number: String,
    private var email_address: String,
    private var biography: String,
    private var photo_url_small: String,
    private var photo_url_large: String,
    private var team: String,
    private var employee_type: String
) {

    fun getFullName(): String {
        return full_name
    }
}
