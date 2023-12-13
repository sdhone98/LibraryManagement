package com.sagardhone.library_management.student

import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(var studentRepository: StudentRepository):StudentService {

    override fun getAllStudentDetails(): List<Student> = studentRepository.findAll()

    override fun getStudentDetailsById(studentId: String): Student = studentRepository.findById(studentId).orElseThrow({RuntimeException("Student not found.")})

    override fun addStudentDetails(student: Student): Student = studentRepository.save(student)

    override fun updateStudentDetailsById(studentId: String, student: Student): Student {
        val oldDetails =  studentRepository.findById(studentId).orElseThrow({RuntimeException("Student not found.")})
        oldDetails.firstName = student.firstName
        oldDetails.lastName = student.lastName
        oldDetails.age = student.age
        oldDetails.year = student.year
        oldDetails.department = student.department
        return studentRepository.save(oldDetails)
    }

    override fun removeStudentDetailsById(studentId: String): String {
        studentRepository.deleteById(studentId)
        return "Student details removed for Student Id : $studentId"
    }

    override fun removeMultipleStudentDetailsByIds(studentIds: List<String>): String {
        studentRepository.deleteAllByIdInBatch(studentIds)
        return "Student details removed for these Student Ids : $studentIds"
    }
}