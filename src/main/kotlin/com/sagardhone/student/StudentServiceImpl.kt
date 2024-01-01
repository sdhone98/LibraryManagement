package com.sagardhone.library_management.student

import com.sagardhone.student.StudentRepository
import exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class StudentServiceImpl(var studentRepository: StudentRepository):StudentService {

    override fun getAllStudentDetails(): List<Student> = studentRepository.findAll()

    override fun getStudentDetailsById(studentId: String): Student {

        val foundStudent: Optional<Student> = studentRepository.findById(studentId)

        if (foundStudent.isPresent()) return foundStudent.get() else throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Given student id $studentId not exist.!")
    }

    override fun addStudentDetails(student: Student): Student = studentRepository.save(student)

    override fun updateStudentDetailsById(studentId: String, student: Student): Student {
        val foundStudent: Optional<Student> = studentRepository.findById(studentId)

        if (foundStudent.isPresent){
            val oldDetails =  foundStudent.get()
            oldDetails.firstName = student.firstName
            oldDetails.lastName = student.lastName
            oldDetails.age = student.age
            oldDetails.year = student.year
            return studentRepository.save(oldDetails)
        }else{
            throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Given student id $studentId not exist.!")
        }

    }

    override fun removeStudentDetailsById(studentId: String): String {
        val foundStudent: Optional<Student> = studentRepository.findById(studentId)

        if (foundStudent.isPresent){
            studentRepository.deleteById(studentId)
            return "Student details removed for Student Id : $studentId"
        }else{
            throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Given student id $studentId not exist.!")
        }

    }

    override fun removeMultipleStudentDetailsByIds(studentIds: List<String>): String {
        val foundStudent: List<String> = studentRepository.availableStudentIds(studentIds)

        if (foundStudent.isEmpty()) throw CustomException(HttpStatus.NOT_FOUND.value(),"Given student ids $studentIds not exist.!")
        studentRepository.deleteAllByIdInBatch(foundStudent)
        return "Student details removed for these Book Ids : $foundStudent, \nthese ids not available ${studentIds.toSet() - foundStudent.toSet()}."
    }
}