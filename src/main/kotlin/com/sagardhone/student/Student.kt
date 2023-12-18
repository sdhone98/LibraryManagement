package com.sagardhone.library_management.student
import com.sagardhone.department.Department
import jakarta.persistence.*
import jakarta.validation.constraints.Min


@Entity
@Table(name = "student")
data class Student (

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    var id: String,

    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    var firstName: String,

    @Column(name = "LAST_NAME", length = 100, nullable = false)
    var lastName: String,

    @Column(name = "AGE", nullable = false)
    @Min(value = 18, message = "Student age should be greater than 18 years.!")
    var age: Int,

    @Column(name = "ACADEMIC_YEAR", nullable = false)
    var year: Int,

    @ManyToOne(cascade = [CascadeType.ALL])
    var department: Department,



    ){

    constructor() : this("", "", "", 0, 0,Department())
    constructor(
        fName:String,
        lName:String,
        age:Int,
        year: Int,
        department: Department): this("1",fName, lName, age, year, department)

}
