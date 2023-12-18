package com.sagardhone.order

import com.sagardhone.library_management.student.Student
import com.sagardhone.library_management.student.StudentRepository
import com.sagardhone.textBook.TextBook
import com.sagardhone.textBook.TextBookRepository
import exception.CustomException
import org.springframework.http.HttpStatus
import java.text.SimpleDateFormat
import java.util.*


class OrderServiceImpl(var orderRepository: OrderRepository, var textBookRepository: TextBookRepository, var studentRepository: StudentRepository):OrderService {
    override fun getAllOrderDetails(): List<Order> = orderRepository.findAll()

    override fun getOrderDetailById(orderId: String): Order {
        val foundData:Optional<Order> =  orderRepository.findById(orderId)

        if (!foundData.isPresent) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Given order id $orderId not exist.!")

        return foundData.get()
    }

    override fun placeOrder(studentId: String, textBookId: String): Order {
        /* CHECK STUDENT ID VALID OR NOT*/
        val foundStudent:Optional<Student> = studentRepository.findById(studentId)

        if (!foundStudent.isPresent) throw CustomException(HttpStatus.NOT_FOUND.value(),"Given student id $studentId not exist.!")



        /* CHECK HOW MUCH BOOKS OWN BY STUDENT*/
        val foundStudentData:List<Order> = orderRepository.getAllOrdersByStudentId(studentId)

        if (foundStudentData.count() > 3 ) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Student already owned 3 books you are not allowed to borrow new book.!")

        /* CHECK SAME BOOK OR NOT*/
        foundStudentData.forEach {
            if (it.textbook.equals(textBookId)) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Given book is already borrowed by student.!")
        }


        /* CHECK HOW MUCH BOOK EXIST OR NOT*/
        val foundBookData:Optional<TextBook> = textBookRepository.findById(textBookId)

        if (!foundBookData.isPresent) throw CustomException(HttpStatus.NOT_FOUND.value(),"Given book id $textBookId not exist.!")
        val order:Order = Order()

        order.orderDateTime = java.util.Date()
        order.student = foundStudent.get()
        order.student = foundStudent.get()
        return Order()
    }

    override fun returnTextBook(studentId: String, textBookId: String): String {
        TODO("Not yet implemented")
    }

    override fun getStudentOwnBooksDetails(studentId: String): List<Order> {
        TODO("Not yet implemented")
    }

    override fun getStudentDetailsByBookId(textBookId: String): List<Student> {
        TODO("Not yet implemented")
    }

    override fun remainingQuantityOfBookById(textBookId: String): Order {
        TODO("Not yet implemented")
    }
}