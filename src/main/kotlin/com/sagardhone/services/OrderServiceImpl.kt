package com.sagardhone.services

import com.sagardhone.models.Student
import com.sagardhone.models.Order
import com.sagardhone.repositories.StudentRepository
import com.sagardhone.models.TextBook
import com.sagardhone.repositories.OrderRepository
import com.sagardhone.repositories.TextBookRepository
import exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderServiceImpl(var orderRepository: OrderRepository, var textBookRepository: TextBookRepository, var studentRepository: StudentRepository):
    OrderService {
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


        /* CHECK BOOK ID VALID OR NOT*/
        val foundBook:Optional<TextBook> = textBookRepository.findById(textBookId)

        if (!foundBook.isPresent) throw CustomException(HttpStatus.NOT_FOUND.value(),"Book id '$textBookId' not exist.!")

        /* CHECK BOOK AVAILABLE OR NOT */

        if (foundBook.get().quantity <= 0) throw CustomException(HttpStatus.NOT_FOUND.value(),"'${foundBook.get().name}' is currently out of stock. please try later.!")


        /*CHECK BOOK LIMIT*/
        if (orderRepository.getCountOfOrdersByStudentId(studentId) >= 3) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(), "You cannot borrow more that 3 book at one time.!")


        /* CHECK SAME BOOK OR NOT*/
        val foundStudentData:List<Order> = orderRepository.getAllOrdersByStudentId(studentId)

        val isBookAlreadyBorrowed:Boolean = foundStudentData.any { it.textbook.id == textBookId }

        if (isBookAlreadyBorrowed) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(), "Given book is already borrowed by the student!")


        /* CHECK HOW MUCH BOOK EXIST OR NOT*/
        val foundBookData:Optional<TextBook> = textBookRepository.findById(textBookId)

        if (!foundBookData.isPresent) throw CustomException(HttpStatus.NOT_FOUND.value(),"Given book id $textBookId not exist.!")

        /* CREATE A NEW ORDER */
        val newOrder = Order(foundStudent.get(), foundBookData.get(), Date())
        orderRepository.save(newOrder)

        /* UPDATE BOOK QUANTITY */
        val updateBookQuantity = foundBook.get()
        updateBookQuantity.quantity -= 1
        textBookRepository.save(updateBookQuantity)

        orderRepository.save(newOrder)
        return newOrder
    }

    override fun returnTextBook(studentId: String, textBookId: String): String {
        /* CHECK ORDER ID VALID OR NOT */
        val foundOrderData: Order = orderRepository.getOrderByStudentIdAndTextBookId(studentId,textBookId)
            ?: throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Given order not found.!")
        val bookName:String = foundOrderData.textbook.name
        foundOrderData.student = Student()
        foundOrderData.textbook = TextBook()
        orderRepository.save(foundOrderData)
        orderRepository.deleteById(foundOrderData.id)


        /* UPDATE BOOK QUANTITY */

        val foundBook:Optional<TextBook> = textBookRepository.findById(textBookId)

        val updateBookQuantity = foundBook.get()
        updateBookQuantity.quantity += 1
        textBookRepository.save(updateBookQuantity)

        return "\"$bookName\" book has been return."
    }

    override fun getStudentBorrowedBooksDetailsByStudentId(studentId: String): List<TextBook> = orderRepository.getTextBookDetailsByStudentId(studentId)

    override fun getBookBorrowedStudentDetailsByBookId(textBookId: String): List<Student> = orderRepository.getStudentDetailsByTextBookId(textBookId)

    override fun remainingQuantityOfBookById(textBookId: String): Int = textBookRepository.getQuantityByTextBookId(textBookId)
}