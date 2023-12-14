package com.sagardhone.department
import exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class DepartmentServiceImpl(var departmentRepository: DepartmentRepository): DepartmentService {
    override fun getAllDepartments(): List<Department> = departmentRepository.findAll()

    override fun addDepartment(department: Department): Department {
        return departmentRepository.save(department)
    }

    override fun removeDepartment(departmentId: String): String {
        val foundDept: Optional<Department> = departmentRepository.findById(departmentId)

        if (foundDept.isPresent) return "Department details removed\n ${foundDept.get()}" else throw CustomException(
            HttpStatus.NOT_FOUND.value(),"Given department id $departmentId not exist.!")
    }
}