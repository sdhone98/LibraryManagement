package com.sagardhone.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository:JpaRepository<Order, String>{
    @Query("SELECT o from Order o WHERE o.student = :studentId")
    fun getAllOrdersByStudentId(studentId: String):List<Order>
}