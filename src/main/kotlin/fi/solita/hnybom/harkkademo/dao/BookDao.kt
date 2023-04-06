package fi.solita.hnybom.harkkademo.dao

import fi.solita.hnybom.harkkademo.Tables
import fi.solita.hnybom.harkkademo.tables.pojos.Book
import org.jooq.DSLContext
import org.springframework.stereotype.Repository


@Repository
class BookDao(val dslContext: DSLContext ) {

    fun getBooks(): List<Book> {
        return dslContext.selectFrom(Tables.BOOK).fetchInto(Book::class.java)
    }

}