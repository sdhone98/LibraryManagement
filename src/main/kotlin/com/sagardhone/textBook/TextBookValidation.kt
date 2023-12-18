package com.sagardhone.textBook
import exception.CustomException
import org.springframework.http.HttpStatus

class TextBookValidation {


}

fun checkBookName(textBookName:String){

    if (textBookName.isEmpty()) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Book name shouldn't be empty.!")

    if (textBookName.isNullOrBlank()) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Book name shouldn't be empty.!")

    if (!"^[A-Za-z0-9 -&]*$".toRegex().matches(textBookName)) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Book name should be alphanumeric values except -&.!")
}

fun checkBookPageCount(textBookPageCount:Int){

    if (textBookPageCount <= 0 ) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Page count should be greater than zero.!")

    if (textBookPageCount >= 1000) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Page count should be under 1000.!")
}


fun checkBookPrice(textBookPrice:Double) {

    if (textBookPrice >= 10000 ){
        throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Book price under 10,000.!")
    }
}

fun checkBookIds(textBookIds:List<String>) {

    for (id in textBookIds){
        if (!id.startsWith('B')) throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Given book id $id is invalid.!")
    }
}

fun validateBookObject(textBook: TextBook){
    checkBookName(textBook.name)
    textBook.pageCount?.let { checkBookPageCount(it) }
    checkBookPrice(textBook.price)
}