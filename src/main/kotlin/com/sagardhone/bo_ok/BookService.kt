package com.sagardhone.library_management.bo_ok

interface BookService {

    fun getAllBooks(): List<Bo_ok>

    fun getBookById(bookId:String): Bo_ok

    fun addBook(book: Bo_ok): Bo_ok

    fun updateBookDetailsById(bookId: String, book: Bo_ok): Bo_ok

    fun removeBookById(bookId: String): String

    fun removeMultipleBooksByIds(bookIds: List<String>): String
}