package com.sagardhone.order

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sagardhone.library_management.student.Student
import com.sagardhone.textBook.TextBook
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name = "book_order")
class Order(

    @Id
    @GeneratedValue(generator = "order-custom-id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "order-custom-id", strategy = "com.sagardhone.customIdGenerator.OrderCustomIdGenerator")
    @Column(name = "ID", nullable = false, updatable = false)
    var id: String,

    @ManyToOne(cascade = [CascadeType.ALL], optional = false)
    @JsonManagedReference
    var student: Student,

    @ManyToOne(cascade = [CascadeType.ALL], optional = false)
    @JsonManagedReference
    var textbook: TextBook,

    @Column(name ="ORDER_DATE_TIME", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    var orderDateTime: Date,
) {
    constructor() : this("", Student(), TextBook(), Date())
    constructor(
        student: Student,
        textbook: TextBook,
        date: Date
    ) : this("11", student, textbook, date)
}
