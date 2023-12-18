package com.sagardhone.customIdGenerator

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import java.io.Serializable
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException

class BookCustomIdGenerator : IdentifierGenerator {
    @Throws(HibernateException::class)
    override fun generate(session: SharedSessionContractImplementor, `object`: Any): Serializable {
        val prefix = "B_"
        val query = "SELECT MAX(id) FROM Bo_ok"
        val connection: Connection = session.jdbcConnectionAccess.obtainConnection()

        try {
            connection.prepareStatement(query).use { statement ->
                val rs: ResultSet = statement.executeQuery()
                if (rs.next()) {
                    val lastId = rs.getString(1)
                    if (lastId != null) {
                        val number = lastId.substring(prefix.length).toInt() + 1
                        return "$prefix$number"
                    }
                }
            }
        } catch (e: SQLException) {
            throw HibernateException("Unable to generate ID", e)
        }

        return "$prefix+1"
    }
}