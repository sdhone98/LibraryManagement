package com.sagardhone.library_management.bo_ok
import com.sagardhone.bo_ok.validateBookObject
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
    fun updateBookDetails(@PathVariable bookId: String, @RequestBody book: Bo_ok) = bookService.updateBookDetailsById(bookId, book)

    @DeleteMapping("/{bookId}")
    fun removeBookDetailsById(@PathVariable bookId: String) = bookService.removeBookById(bookId)

    @DeleteMapping()
    fun removeMultipleBookDetailsById(@RequestBody bookId: List<String>) = bookService.removeMultipleBooksByIds(bookId)
}