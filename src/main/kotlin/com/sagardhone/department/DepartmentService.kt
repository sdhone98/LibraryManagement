package com.sagardhone.department


interface DepartmentService {

    fun getAllDepartments():List<Department>
    fun getDepartmentBYId(departmentId:String):Department

    fun addDepartment(department: Department): Department

    fun removeDepartment(departmentId:String):String
}