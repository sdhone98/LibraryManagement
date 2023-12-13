package com.sagardhone.library_management.student

import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/student")
class StudentController(var studentService: StudentService) {

    @GetMapping
    fun getAllStudentDetails(): List<Student> = studentService.getAllStudentDetails()

    @GetMapping("/{studentId}")
    fun getStudentDetailsById(@PathVariable studentId: String)= studentService.getStudentDetailsById(studentId)

    @PostMapping()
    fun addStudentDetails(@RequestBody student: Student): Student = studentService.addStudentDetails(student)

    @PutMapping("/{studentId}")
    fun updateStudentDetails(@PathVariable studentId: String, @RequestBody student: Student) = studentService.updateStudentDetailsById(studentId, student)

    @DeleteMapping("/{studentId}")
    fun removeStudentDetailsById(@PathVariable studentId: String) = studentService.removeStudentDetailsById(studentId)

    @DeleteMapping()
    fun removeMultipleStudentDetailsById(@RequestBody studentIds: List<String>) = studentService.removeMultipleStudentDetailsByIds(studentIds)

}