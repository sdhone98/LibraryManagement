package com.sagardhone.textBook
import exception.CustomException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TextBookServiceImpl(var textBookRepository: TextBookRepository):TextBookService {

    override fun getAllBooks(): List<TextBook> = textBookRepository.findAll()

    override fun getBookById(textBookId: String): TextBook {
        val book: Optional<TextBook> = textBookRepository.findById(textBookId)

        if (book.isPresent()) {
            return book.get()
        } else {
            throw CustomException(HttpStatus.NOT_FOUND.value(),"Book id '$textBookId' not exist.!")
        }

    }

    @Transactional
    override fun addBook(textBook: TextBook): TextBook {

        if (textBookRepository.existsByName(textBook.name)) throw CustomException(HttpStatus.IM_USED.value(),"${textBook.name} already exist name should be unique.!")

        return textBookRepository.save(textBook)

    }

    override fun updateBookDetailsById(textBookId: String, book: TextBook): TextBook {

        val oldDetails: Optional<TextBook> = textBookRepository.findById(textBookId)

        if (oldDetails.isPresent()) {
            val nameExist:Boolean = textBookRepository.existsByNameAndIdNot(textBookId,book.name)

            if (nameExist == true){
                throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"'${book.name}\' already exist name should be unique.!")
            }
            val existingBook = oldDetails.get()
            existingBook.name = book.name
            existingBook.publication = book.publication
            existingBook.pageCount = book.pageCount
            existingBook.price = book.price
            existingBook.publicationDate = book.publicationDate
            existingBook.quantity = book.quantity
            return textBookRepository.save(existingBook)
        } else {
            throw CustomException(HttpStatus.NOT_FOUND.value(),"Book id '$textBookId' not exist.!")
        }

    }

    override fun removeBookById(textBookId: String): String {

        val book: Optional<TextBook> = textBookRepository.findById(textBookId)

        if (book.isPresent()) {
            return "Book details removed for Book Id : $textBookId"
        } else {
            throw CustomException(HttpStatus.NOT_FOUND.value(),"Book id '$textBookId' not exist.!")
        }

    }

    override fun removeMultipleBooksByIds(textBookId: List<String>): String {

        val foundIds:List<String> = textBookRepository.availableBookIds(textBookId)

        if (foundIds.isEmpty()) throw CustomException(HttpStatus.NOT_FOUND.value(),"Given book ids $textBookId not exist.!")

        textBookRepository.deleteAllByIdInBatch(foundIds)

        return "Book details removed for these Book Ids : $foundIds, \nthese ids not available ${textBookId.toSet() - foundIds.toSet()}."
    }
}