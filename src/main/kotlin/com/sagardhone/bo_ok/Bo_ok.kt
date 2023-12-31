package com.sagardhone.library_management.bo_ok

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.GenericGenerator
import java.util.Date

@Entity
@Table(name = "books")
data class Bo_ok (

    @Id
    @Column(name = "ID", nullable = false, updatable = false, unique = true)
    var id: String,

    @Column(name = "NAME", nullable = false, length = 300, unique = true)
    @NotBlank(message = "name should not be empty.")
    var name: String,

    @Column(name="PAGE_COUNT", nullable = true)
    var pageCount: Int?,

    @Column(name ="PUBLICATION_DATE", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    var publicationDate: Date?,

    @Column(name ="PRICE", nullable = false)
    var price: Double,
){

    // Secondary constructor without id
    constructor(
        nameParam: String,
        pageCountParam: Int?,
        publicationDateParam: Date?,
        priceParam: Double
    ) : this("1", nameParam, pageCountParam, publicationDateParam, priceParam)

    // Empty constructor for JPA
    constructor() : this("", "", null, null, 0.0)
}