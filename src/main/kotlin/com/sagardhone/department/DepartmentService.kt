package com.sagardhone.department

import com.sagardhone.library_management.student.Student


interface DepartmentService {

    fun getAllDepartments():List<Department>
    fun getDepartmentBYId(departmentId:String):Department

    fun addDepartment(department: Department): Department

    fun removeDepartment(departmentId:String):String

    fun getStudentDetailsByDepartmentId(departmentId: String): List<Student>
}