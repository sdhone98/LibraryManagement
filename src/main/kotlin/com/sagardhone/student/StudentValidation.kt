package com.sagardhone.student

import com.sagardhone.library_management.student.Student
import exception.CustomException
import org.springframework.http.HttpStatus

class StudentValidation {
}

fun checkStudentName(firstName:String, lastName:String){
    if (firstName.isNullOrBlank() or firstName.isEmpty()) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Student first name should not be empty or blank.!")

    if (lastName.isNullOrBlank() or firstName.isEmpty()) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Student last name should not be empty or blank.!")

    if(!firstName.all { it.isLetter() }) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Student first name should be in alphabets only.!")

    if(!lastName.all { it.isLetter() }) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Student first name should be in alphabets only.!")
}



fun checkStudentAge(studentAge:Int){
    if (studentAge <= 18 ) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Student age should be greater than 18 years.!")

    if (studentAge >= 130) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Please enter valid age.!")
}

fun checkStudentAcademicYear(studentYear:Int){
    if (studentYear <= 1) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Minimum academic year is 1")

    if (studentYear >= 4) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Maximum academic year is 4")
}

//fun checkDepartment(depart:String){
//    if (!studentDepartment.all { it.isLetter() }) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Invalid department name.!")
//}

fun checkStudentIds(studentIds:List<String>) {

    for (id in studentIds){
        if (!id.startsWith('S')) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Given student id $id is invalid.!")
    }
}
fun checkStudentObject(student:Student){
    checkStudentName(student.firstName, student.lastName)
    checkStudentAge(student.age)
    checkStudentAcademicYear(student.year)
}