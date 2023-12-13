package com.sagardhone.bo_ok

import com.sagardhone.library_management.bo_ok.Bo_ok
import exception.CustomException
import org.springframework.http.HttpStatus

class BookValidation {


}

fun checkBookName(bookName:String){

    if (bookName.isEmpty()){
        throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Book name shouldn't be empty.!")
    }

    if (bookName.isNullOrBlank()){
        throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Book name shouldn't be empty.!")
    }

    if (!"^[A-Za-z0-9 -]*$".toRegex().matches(bookName)){
        throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Book name should be alphanumeric value.!")
    }
}

fun checkBookPageCount(bookPageCount:Int){

    if (bookPageCount <= 0 ){
        throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Page count should be greater than zero.!")
    }

    if (bookPageCount >= 1000){
        throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Page count should be under 1000.!")
    }
}


fun checkBookPrice(bookPrice:Double) {

    if (bookPrice >= 10000 ){
        throw CustomException(HttpStatus.NOT_ACCEPTABLE.value(),"Book price under 10,000.!")
    }
}

fun validateBookObject(book:Bo_ok){
    checkBookName(book.name)
    book.pageCount?.let { checkBookPageCount(it) }
    checkBookPrice(book.price)
}
