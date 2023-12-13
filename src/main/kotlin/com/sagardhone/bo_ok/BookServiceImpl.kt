package com.sagardhone.library_management.bo_ok

import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class BookServiceImpl(var bookRepository: BookRepository): BookService {

    val stringToDateFormatter = SimpleDateFormat("dd-MMM-yyyy")

    override fun getAllBooks(): List<Bo_ok> = bookRepository.findAll()

    override fun getBookById(bookId: String): Bo_ok = bookRepository.findById(bookId).orElseThrow({RuntimeException("Book not found.")})

    override fun addBook(book: Bo_ok): Bo_ok {
        println("BOOK DETAILS : "+book)
        println("DATE CONV : "+book.publicationDate)
        return bookRepository.save(book)
    }

    override fun updateBookDetailsById(bookId: String, book: Bo_ok): Bo_ok {
        val oldDetails =  bookRepository.findById(bookId).orElseThrow({RuntimeException("Book not found.")})
        oldDetails.name = book.name
        oldDetails.pageCount = book.pageCount
        oldDetails.price = book.price
        oldDetails.publicationDate = book.publicationDate
        return bookRepository.save(oldDetails)
    }

    override fun removeBookById(bookId: String): String {
        return "Book details removed for Book Id : $bookId"
    }

    override fun removeMultipleBooksByIds(bookIds: List<String>): String {
        val foundData = bookRepository.deleteAllByIdInBatch(bookIds)
        return "Book details removed for these Book Ids : $bookIds"
    }
}