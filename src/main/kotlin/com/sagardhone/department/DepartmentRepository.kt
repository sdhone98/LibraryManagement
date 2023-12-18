package com.sagardhone.department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository:JpaRepository<Department, String>{

    fun existsByName(name: String): Boolean
}