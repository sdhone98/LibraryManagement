package com.sagardhone.library_management.student

import com.sagardhone.order.OrderService
import com.sagardhone.student.*
import com.sagardhone.textBook.TextBook
import exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/student")
class StudentController(var studentService: StudentService, var orderService: OrderService) {

    @GetMapping
    fun getAllStudentDetails(): List<Student> = studentService.getAllStudentDetails()

    @GetMapping("/{studentId}")
    fun getStudentDetailsById(@PathVariable studentId: String)= studentService.getStudentDetailsById(studentId)

    @PostMapping()
    fun addStudentDetails(@RequestBody student: Student): Student {

        /* VALIDATE STUDENT OBJ */
        checkStudentObject(student)
        return studentService.addStudentDetails(student)
    }

    @PutMapping("/{studentId}")
    fun updateStudentDetails(@PathVariable studentId: String, @RequestBody student: Student):Student {

        /* VALIDATE STUDENT OBJ */
        checkStudentObject(student)

        return studentService.updateStudentDetailsById(studentId, student)
    }

    @DeleteMapping("/{studentId}")
    fun removeStudentDetailsById(@PathVariable studentId: String):String{

        if (!studentId.startsWith('S')) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Given student id $studentId is invalid.!")
        return studentService.removeStudentDetailsById(studentId)
    }

    @DeleteMapping()
    fun removeMultipleStudentDetailsById(@RequestBody studentIds: List<String>):String {

        /* VALIDATE STUDENT OBJ */
        checkStudentIds(studentIds)

        return studentService.removeMultipleStudentDetailsByIds(studentIds)
    }

    @GetMapping("/{studentId}/books")
    fun getStudentBorrowedBooksDetailsByStudentId(@PathVariable studentId: String): List<TextBook> = orderService.getStudentBorrowedBooksDetailsByStudentId(studentId)


}