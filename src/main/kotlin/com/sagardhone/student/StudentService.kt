package com.sagardhone.library_management.student

interface StudentService {

    fun getAllStudentDetails(): List<Student>

    fun getStudentDetailsById(studentId:String): Student

    fun addStudentDetails(student: Student): Student

    fun updateStudentDetailsById(studentId: String, student: Student): Student

    fun removeStudentDetailsById(studentId: String): String

    fun removeMultipleStudentDetailsByIds(studentIds: List<String>): String
    fun getAllStudentsByDepartment(departmentId: String): List<Student>

}