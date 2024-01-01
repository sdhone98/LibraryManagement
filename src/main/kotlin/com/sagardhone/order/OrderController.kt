package com.sagardhone.order

import com.sagardhone.library_management.student.Student
import com.sagardhone.textBook.TextBook
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController (var orderService: OrderService){

    @GetMapping
    fun getAllOrders(): List<Order> = orderService.getAllOrderDetails()

    @GetMapping("/{orderID}")
    fun getOrderDetailsById(@PathVariable orderID: String): Order = orderService.getOrderDetailById(orderID)

    @PostMapping("place_order/{studentId}/{textBookId}")
    fun placeOrder(@PathVariable studentId: String, @PathVariable textBookId: String):Order = orderService.placeOrder(studentId, textBookId)

    @PostMapping("return_book/{studentId}/{textBookId}")
    fun returnTextBook(@PathVariable studentId: String, @PathVariable textBookId: String): String = orderService.returnTextBook(studentId, textBookId)


    @GetMapping("student/{studentId}")
    fun getStudentBorrowedBooksDetailsByStudentId(@PathVariable studentId: String): List<TextBook> = orderService.getStudentBorrowedBooksDetailsByStudentId(studentId)


    @GetMapping("book/{textBookId}")
    fun getBookBorrowedStudentDetailsByBookId(@PathVariable textBookId: String): List<Student> = orderService.getBookBorrowedStudentDetailsByBookId(textBookId)

    @GetMapping("book_details/{textBookId}")
    fun remainingQuantityOfBookById(@PathVariable textBookId: String): Int = orderService.remainingQuantityOfBookById(textBookId)


}