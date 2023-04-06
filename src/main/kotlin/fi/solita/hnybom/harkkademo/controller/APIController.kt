package fi.solita.hnybom.harkkademo.controller

import fi.solita.hnybom.harkkademo.dao.BookDao
import fi.solita.hnybom.harkkademo.model.Message
import fi.solita.hnybom.harkkademo.tables.pojos.Book
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    path = ["api"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@CrossOrigin(origins = ["*"])
class APIController(val bookDao: BookDao) {
    @GetMapping(value = ["/public"])
    fun publicEndpoint(): List<Book> {
        return bookDao.getBooks()
    }

    @GetMapping(value = ["/private"])
    fun privateEndpoint(): Message {
        return Message("All good. You can see this because you are Authenticated.")
    }

}