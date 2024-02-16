package com.sagardhone.validations

import com.sagardhone.models.Department
import exception.CustomException
import org.springframework.http.HttpStatus

class DepartmentValidation {
}

fun checkDepartmentId(departmentId:String) {
    if (!departmentId.startsWith("D_")) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(), "Please enter correct department id eg.D_0")
}


fun departmentName(departmentName:String) {
    if (!"^[A-Za-z -]*$".toRegex().matches(departmentName)) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(), "Special character and numbers are not allowed in department name except '-'.")

}


fun departmentCodeCheck(departmentCode:String){
    if (departmentCode.isEmpty() or departmentCode.isBlank()) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(), "Please enter department code.")

    if (!"^[A-Z]*$".toRegex().matches(departmentCode)) throw CustomException(HttpStatus.NOT_FOUND.value(), "Only Uppercase characters allowed in department code.")

    if (departmentCode.length > 4 ) throw CustomException(HttpStatus.IM_USED.value(), "Department code length should be less than or equal to 4 characters.")


}


fun checkDepartmentObject(department: Department){
    departmentName(department.name)
    departmentCodeCheck(department.code)
}