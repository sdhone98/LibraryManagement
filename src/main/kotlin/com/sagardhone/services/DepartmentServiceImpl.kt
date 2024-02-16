package com.sagardhone.services
import com.sagardhone.models.Student
import com.sagardhone.services.StudentService
import com.sagardhone.models.Department
import com.sagardhone.repositories.DepartmentRepository
import com.sagardhone.services.DepartmentService
import exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class DepartmentServiceImpl(var departmentRepository: DepartmentRepository, private val studentService: StudentService):
    DepartmentService {
    override fun getAllDepartments(): List<Department> = departmentRepository.findAll()
    override fun getDepartmentBYId(departmentId: String): Department {
        val foundDepartment: Optional<Department> = departmentRepository.findById(departmentId)

        if (!foundDepartment.isPresent) throw CustomException(HttpStatus.NOT_FOUND.value(),"Given department id : $departmentId not exist please enter valid id.!")
        return foundDepartment.get()
    }

    override fun addDepartment(department: Department): Department {
        /* CHECK DEPARTMENT NAME ALL READY EXIST OR NOT*/
        val isDepartmentNameExist:Boolean = departmentRepository.existsByName(department.name)

        if (departmentRepository.existsByName(department.name)) throw CustomException(HttpStatus.NOT_FOUND.value(),"'${department.name}' is already exist department name should be unique.!")
        return departmentRepository.save(department)
    }

    override fun removeDepartment(departmentId: String): String {
        val foundDept: Optional<Department> = departmentRepository.findById(departmentId)

        val isDepartmentE: Unit = departmentRepository.deleteById(departmentId)

        if (!foundDept.isPresent) throw CustomException(HttpStatus.NOT_FOUND.value(), "Given department id $departmentId not exist.!")

        departmentRepository.deleteById(departmentId)
        return "${foundDept.get().name} Department has been removed"
    }

    override fun getStudentDetailsByDepartmentId(departmentId: String): List<Student> = getDepartmentBYId(departmentId).students
}