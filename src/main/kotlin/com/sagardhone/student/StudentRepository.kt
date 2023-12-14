package com.sagardhone.library_management.student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository: JpaRepository<Student, String>{
    @Query("SELECT id FROM Student WHERE id IN :studentIds")
    fun availableStudentIds(studentIds: List<String>): List<String>
}

