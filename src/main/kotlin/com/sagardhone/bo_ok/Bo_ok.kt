package com.sagardhone.library_management.bo_ok

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.util.Date

@Entity
@GenericGenerator(
    name = "custom-id",
    strategy = "com.sagardhone.library_management.id_generator.CustomIdBookIdGenerator"
)
@Table(name = "books")
data class Bo_ok (
    @Id
    @GeneratedValue(generator = "custom-id")
    @Column(name = "ID", nullable = false, updatable = false, unique = true)
    var id: String,

    @Column(name = "NAME", nullable = false, length = 300, unique = true)
    var name: String,

    @Column(name="PAGE_COUNT", nullable = true)
    var pageCount: Int?,

    @Column(name ="PUBLICATION_DATE", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    var publicationDate: Date?,

    @Column(name ="PRICE", nullable = false)
    var price: Double,
){
    constructor():this("","",null,null,0.0)
}