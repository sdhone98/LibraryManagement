package com.sagardhone.textBook

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import org.hibernate.annotations.GenericGenerator
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "textbook")
class TextBook (

    @Id
    @GeneratedValue(generator = "text-book-custom-id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "text-book-custom-id", strategy = "com.sagardhone.customIdGenerator.TextBookCustomIdGenerator")
    @Column(name = "ID", nullable = false, updatable = false)
    var id: String,

    @Column(name = "NAME", nullable = false, length = 300, unique = true)
    @NotBlank(message = "name should not be empty.")
    var name: String,

    @Column(name = "PUBLICATION", nullable = false, length = 300)
    @NotBlank(message = "publication name should not be empty.")
    var publication: String,

    @Column(name="PAGE_COUNT", nullable = true)
    var pageCount: Int?,

    @Column(name ="PUBLICATION_DATE", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    var publicationDate: Date?,

    @Column(name ="PRICE", nullable = false)
    var price: Double,

    @Column(name ="QUANTITY", nullable = false)
    var quantity: Int,
){

    // Secondary constructor without id
    constructor(
        nameParam: String,
        publicationParam: String,
        pageCountParam: Int?,
        publicationDateParam: Date?,
        priceParam: Double,
        quantityParam:Int
    ) : this("1", nameParam, publicationParam,pageCountParam, publicationDateParam, priceParam, quantityParam)

    // Empty constructor for JPA
    constructor() : this("", "","", null, null, 0.0, 1)
}