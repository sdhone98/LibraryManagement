package com.sagardhone.order

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sagardhone.library_management.student.Student
import com.sagardhone.textBook.TextBook
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name = "department")
class Order(

    @Id
    @GeneratedValue(generator = "order-custom-id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "order-custom-id", strategy = "com.sagardhone.customIdGenerator.DepartmentCustomIdGenerator")
    @Column(name = "ID", nullable = false, updatable = false)
    var id: String,

    @Column(name = "STUDENT_ID", nullable = false)
    @OneToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    var student: Student,

    @Column(name = "TEXT_BOOK_ID", nullable = false)
    @OneToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    var textbook: TextBook,


    @Column(name ="ORDER_DATE_TIME", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    var orderDateTime: Date,

){
    constructor():this("",Student(), TextBook(),Date())
}
