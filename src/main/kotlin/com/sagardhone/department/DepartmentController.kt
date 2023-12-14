package com.sagardhone.department
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/department")
class DepartmentController(var departmentService: DepartmentService) {

    @GetMapping
    fun getAllDepartments():List<Department> = departmentService.getAllDepartments()
    @PostMapping
    fun addDepartment(@RequestBody department: Department): Department = departmentService.addDepartment(department)
    @DeleteMapping
    fun removeDepartment(@PathVariable departmentId: String):String = departmentService.removeDepartment(departmentId)
}