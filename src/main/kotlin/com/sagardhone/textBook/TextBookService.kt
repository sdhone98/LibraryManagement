package com.sagardhone.textBook

import com.sagardhone.library_management.student.Student

interface TextBookService {

    fun getAllBooks(): List<TextBook>

    fun getBookById(textBookId:String): TextBook

    fun addBook(textBook: TextBook): TextBook

    fun updateBookDetailsById(textBookId: String, book: TextBook): TextBook

    fun removeBookById(textBookId: String): String

    fun removeMultipleBooksByIds(textBookIds: List<String>): String

    fun getStudentBorrowedBooksDetails(textBookId: String):List<Student>
}