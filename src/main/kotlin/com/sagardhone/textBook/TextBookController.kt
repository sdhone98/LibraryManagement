package com.sagardhone.textBook
import exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/textBook")
class TextBookController (var textBookService: TextBookService) {

    @GetMapping
    fun getAllBooks(): List<TextBook> = textBookService.getAllBooks()

    @GetMapping("/{textBookId}")
    fun getBookById(@PathVariable textBookId: String)= textBookService.getBookById(textBookId)

    @PostMapping()
    fun addBook(@RequestBody textBook: TextBook):TextBook {

        /* VALIDATE AND CHECK BOOK NAME CORRECT OR NOT */
        validateBookObject(textBook)
        return textBookService.addBook(textBook)
    }

    @PutMapping("/{textBookId}")
    fun updateBookDetails(@PathVariable textBookId: String, @RequestBody textBook: TextBook):TextBook {

        /* VALIDATE AND CHECK BOOK NAME CORRECT OR NOT */
        validateBookObject(textBook)

        return textBookService.updateBookDetailsById(textBookId, textBook)
    }

    @DeleteMapping("/{textBookId}")
    fun removeBookDetailsById(@PathVariable textBookId: String):String{
        /* VALIDATE AND CHECK BOOK IDS */
        if (!textBookId.startsWith('B')) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Given book id $textBookId is invalid.!") else return textBookService.removeBookById(textBookId)
    }

    @DeleteMapping()
    fun removeMultipleBookDetailsById(@RequestBody textBookId: List<String>):String {

        /* VALIDATE AND CHECK BOOK IDS */
        checkBookIds(textBookId)
        return textBookService.removeMultipleBooksByIds(textBookId)
    }

    @GetMapping("/{textBookId}/students")
    fun getStudentBorrowedBooksDetails(@PathVariable textBookId: String)= textBookService.getStudentBorrowedBooksDetails(textBookId)
}