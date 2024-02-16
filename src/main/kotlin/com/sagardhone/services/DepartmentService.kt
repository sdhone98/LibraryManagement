package com.sagardhone.services

import com.sagardhone.models.Student
import com.sagardhone.models.Department


interface DepartmentService {

    fun getAllDepartments():List<Department>
    fun getDepartmentBYId(departmentId:String): Department

    fun addDepartment(department: Department): Department

    fun removeDepartment(departmentId:String):String

    fun getStudentDetailsByDepartmentId(departmentId: String): List<Student>
}