package com.sagardhone.library_management.bo_ok
import com.sagardhone.bo_ok.*
import exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookController(var bookService: BookService) {

    @GetMapping
    fun getAllBooks(): List<Bo_ok> = bookService.getAllBooks()

    @GetMapping("/{bookId}")
    fun getBookById(@PathVariable bookId: String)= bookService.getBookById(bookId)

    @PostMapping()
    fun addBook(@RequestBody book: Bo_ok):Bo_ok {

        /* VALIDATE AND CHECK BOOK NAME CORRECT OR NOT */
        validateBookObject(book)
        return bookService.addBook(book)
    }

    @PutMapping("/{bookId}")
    fun updateBookDetails(@PathVariable bookId: String, @RequestBody book: Bo_ok):Bo_ok {

        /* VALIDATE AND CHECK BOOK NAME CORRECT OR NOT */
        validateBookObject(book)

        return bookService.updateBookDetailsById(bookId, book)
    }

    @DeleteMapping("/{bookId}")
    fun removeBookDetailsById(@PathVariable bookId: String):String{
        /* VALIDATE AND CHECK BOOK IDS */
        if (!bookId.startsWith('B')) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Given book id $bookId is invalid.!") else return bookService.removeBookById(bookId)
    }

    @DeleteMapping()
    fun removeMultipleBookDetailsById(@RequestBody bookIds: List<String>):String {

        /* VALIDATE AND CHECK BOOK IDS */
        checkBookIds(bookIds)
        return bookService.removeMultipleBooksByIds(bookIds)
    }
}