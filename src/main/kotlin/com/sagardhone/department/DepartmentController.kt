package com.sagardhone.department
import com.sagardhone.library_management.student.Student
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/department")
class DepartmentController(var departmentService: DepartmentService) {
    @GetMapping
    fun getAllDepartments():List<Department> = departmentService.getAllDepartments()

    @GetMapping("/{departmentId}")
    fun getDepartmentBYId(@PathVariable departmentId: String):Department {
        /* VALIDATE DEPARTED ID*/

        checkDepartmentId(departmentId)
        return departmentService.getDepartmentBYId(departmentId)
    }

    @PostMapping
    fun addDepartment(@RequestBody department: Department): Department {
        /* VALIDATE DEPARTED OBJECT*/
        checkDepartmentObject(department)

        return departmentService.addDepartment(department)
    }

    @DeleteMapping("/{departmentId}")
    fun removeDepartment(@PathVariable departmentId: String): String {
        /* VALIDATE DEPARTED ID*/
        checkDepartmentId(departmentId)
        return departmentService.removeDepartment(departmentId)
    }
    @GetMapping("/{departmentId}/students")
    fun getStudentDetailsByDepartmentId(@PathVariable departmentId: String):List<Student> {
        /* VALIDATE DEPARTED ID*/
        checkDepartmentId(departmentId)
        return departmentService.getStudentDetailsByDepartmentId(departmentId)
    }
}