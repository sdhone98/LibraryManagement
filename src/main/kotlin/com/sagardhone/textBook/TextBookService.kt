package com.sagardhone.textBook

interface TextBookService {

    fun getAllBooks(): List<TextBook>

    fun getBookById(textBookId:String): TextBook

    fun addBook(textBook: TextBook): TextBook

    fun updateBookDetailsById(textBookId: String, book: TextBook): TextBook

    fun removeBookById(textBookId: String): String

    fun removeMultipleBooksByIds(textBookIds: List<String>): String
}