package com.sagardhone.repositories

import com.sagardhone.models.Student
import com.sagardhone.models.Order
import com.sagardhone.models.TextBook
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository:JpaRepository<Order, String>{
    @Query("SELECT o from Order o WHERE o.student.id = :studentId")
    fun getAllOrdersByStudentId(studentId: String):List<Order>
    @Query("SELECT COUNT(*) from Order o WHERE o.student.id = :studentId")
    fun getCountOfOrdersByStudentId(studentId: String):Int

    @Query("SELECT o FROM Order o WHERE o.student.id = :studentId AND o.textbook.id = :textBookId")
    fun getOrderByStudentIdAndTextBookId(studentId: String, textBookId: String): Order?

    @Query("SELECT o.textbook FROM Order o WHERE o.student.id = :studentId")
    fun getTextBookDetailsByStudentId(studentId: String): List<TextBook>


    @Query("SELECT o.student FROM Order o WHERE o.textbook.id = :textBookId")
    fun getStudentDetailsByTextBookId(textBookId: String): List<Student>


}