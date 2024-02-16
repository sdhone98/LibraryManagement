package com.sagardhone.models
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import org.hibernate.annotations.GenericGenerator


@Entity
@Table(name = "student")
data class Student (

    @Id
    @GeneratedValue(generator = "student-custom-id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "student-custom-id", strategy = "com.sagardhone.customIdGenerator.StudentCustomIdGenerator")
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
    @JsonBackReference
    var department: Department,



    ){

    constructor() : this("", "", "", 0, 0, Department())
    constructor(
        fName:String,
        lName:String,
        age:Int,
        year: Int): this("1",fName, lName, age, year, Department())

}
