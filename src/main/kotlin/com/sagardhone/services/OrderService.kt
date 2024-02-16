package com.sagardhone.services

import com.sagardhone.models.Student
import com.sagardhone.models.Order
import com.sagardhone.models.TextBook

interface OrderService {

    fun getAllOrderDetails(): List<Order>

    fun getOrderDetailById(orderId:String): Order

    fun placeOrder(studentId:String, textBookId: String): Order

    fun returnTextBook(studentId:String, textBookId: String): String

    fun getStudentBorrowedBooksDetailsByStudentId(studentId: String):List<TextBook>

    fun getBookBorrowedStudentDetailsByBookId(textBookId: String):List<Student>

    fun remainingQuantityOfBookById(textBookId: String): Int
}