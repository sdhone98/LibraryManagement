package com.sagardhone.library_management.id_generator

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import java.io.Serializable

class CustomIdBookIdGenerator : IdentifierGenerator {

    var counter: Long = 0

    override fun generate(session: SharedSessionContractImplementor, obj: Any): Serializable {
        counter++
        return "B_$counter"
    }
}