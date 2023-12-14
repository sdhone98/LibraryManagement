package com.sagardhone.library_management.bo_ok

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Bo_ok, String>{

    fun existsByName(name: String): Boolean

    fun existsByNameAndIdNot(name: String, id: String): Boolean
    @Query("SELECT id FROM Bo_ok WHERE id IN :booksIds")
    fun availableBookIds(booksIds: List<String>): List<String>

}