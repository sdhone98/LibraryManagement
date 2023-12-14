package com.sagardhone.library_management.student

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.Min
import org.hibernate.annotations.GenericGenerator

@Entity
@GenericGenerator(
    name = "custom-id",
    strategy = "com.sagardhone.library_management.id_generator.CustomIdGenerator"
)
data class Student (
    @Id
    @GeneratedValue(generator = "custom-id")
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

    @Column(name = "DEPARTMENT", length = 100, nullable = false)
    var department: String,
){
    constructor(
        fName:String,
        lName:String,
        age:Int,
        year: Int,
        department: String): this("1",fName,lName,age,year,department)

    constructor():this("0","","",0,0,"")
}