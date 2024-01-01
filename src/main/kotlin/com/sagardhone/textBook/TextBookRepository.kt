package com.sagardhone.textBook

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TextBookRepository: JpaRepository<TextBook, String> {

    fun existsByName(name: String): Boolean

    fun existsByNameAndIdNot(name: String, id: String): Boolean
    @Query("SELECT id FROM TextBook WHERE id IN :textBooksIds")
    fun availableBookIds(textBooksIds: List<String>): List<String>

    @Query("SELECT b.quantity FROM TextBook b WHERE b.id = :textBookId")
    fun getQuantityByTextBookId(textBookId: String): Int

}