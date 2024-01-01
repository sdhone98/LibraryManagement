package com.sagardhone.order

import com.sagardhone.library_management.student.Student
import com.sagardhone.textBook.TextBook

interface OrderService {

    fun getAllOrderDetails(): List<Order>

    fun getOrderDetailById(orderId:String): Order

    fun placeOrder(studentId:String, textBookId: String): Order

    fun returnTextBook(studentId:String, textBookId: String): String

    fun getStudentOwnBooksDetails(studentId: String):List<Order>

    fun getStudentDetailsByBookId(textBookId: String):List<Student>

    fun remainingQuantityOfBookById(textBookId: String): Order
}