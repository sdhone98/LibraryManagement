package com.sagardhone.department
import com.sagardhone.library_management.student.Student
import jakarta.persistence.*

@Entity
@Table(name = "department")
data class Department(

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    var id: String,

    @Column(name = "DEPARTMENT", length = 200, nullable = false)
    var name:String,

    @Column(name = "DEPARTMENT CODE", length = 100, nullable = false)
    var code:String,

    @OneToMany(mappedBy = "department")
    var students: List<Student> = emptyList()

){

    constructor(): this("","","", emptyList())
    constructor(
        name: String,
        code: String): this("","","", emptyList())
}
