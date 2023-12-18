package com.sagardhone.department
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sagardhone.library_management.student.Student
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "department")
data class Department(

    @Id
    @GeneratedValue(generator = "department-custom-id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "department-custom-id", strategy = "com.sagardhone.customIdGenerator.DepartmentCustomIdGenerator")
    @Column(name = "ID", nullable = false, updatable = false)
    var id: String,

    @Column(name = "DEPARTMENT", length = 200, nullable = false)
    var name:String,

    @Column(name = "DEPARTMENT CODE", length = 100, nullable = false)
    var code:String,

    @OneToMany(mappedBy = "department", cascade = [CascadeType.ALL])
    @JsonManagedReference
    var students: List<Student> = emptyList()

){

    constructor(): this("","","", emptyList())
    constructor(
        name: String,
        code: String): this("","","", emptyList())
}
