package com.sagardhone.library_management.bo_ok
import exception.CustomException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class BookServiceImpl(var bookRepository: BookRepository): BookService {

    override fun getAllBooks(): List<Bo_ok> = bookRepository.findAll()

    override fun getBookById(bookId: String): Bo_ok {
        val book: Optional<Bo_ok> = bookRepository.findById(bookId)

        if (book.isPresent()) {
            return book.get()
        } else {
            throw CustomException(HttpStatus.NOT_FOUND.value(),"Book id '$bookId' not exist.!")
        }

    }

    @Transactional
    override fun addBook(@Valid book: Bo_ok): Bo_ok {
        val foundBookName:Boolean = bookRepository.existsByName(book.name)

        if (foundBookName == true){
            throw CustomException(HttpStatus.IM_USED.value(),"${book.name} already exist name should be unique.!")
        }
        book.id = book.name

        return bookRepository.save(book)

    }

    override fun updateBookDetailsById(bookId: String, book: Bo_ok): Bo_ok {

        val oldDetails: Optional<Bo_ok> = bookRepository.findById(bookId)

        if (oldDetails.isPresent()) {
            val nameExist:Boolean = bookRepository.existsByNameAndIdNot(bookId,book.name)

            if (nameExist == true){
                throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"'${book.name}\' already exist name should be unique.!")
            }
            val existingBook = oldDetails.get()
            existingBook.name = book.name
            existingBook.pageCount = book.pageCount
            existingBook.price = book.price
            existingBook.publicationDate = book.publicationDate
            return bookRepository.save(existingBook)
        } else {
            throw CustomException(HttpStatus.NOT_FOUND.value(),"Book id '$bookId' not exist.!")
        }

    }

    override fun removeBookById(bookId: String): String {

        val book: Optional<Bo_ok> = bookRepository.findById(bookId)

        if (book.isPresent()) {
            return "Book details removed for Book Id : $bookId"
        } else {
            throw CustomException(HttpStatus.NOT_FOUND.value(),"Book id '$bookId' not exist.!")
        }

    }

    override fun removeMultipleBooksByIds(bookIds: List<String>): String {

        val foundIds:List<String> = bookRepository.availableBookIds(bookIds)

        if (foundIds.isEmpty()) throw CustomException(HttpStatus.NOT_FOUND.value(),"Given book ids $bookIds not exist.!")

        bookRepository.deleteAllByIdInBatch(foundIds)

        return "Book details removed for these Book Ids : $foundIds, \nthese ids not available ${bookIds.toSet() - foundIds.toSet()}."
    }
}