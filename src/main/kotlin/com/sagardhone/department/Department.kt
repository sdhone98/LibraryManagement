package com.sagardhone.department
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

){

    constructor(): this("","","")
}
