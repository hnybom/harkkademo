package fi.solita.hnybom.harkkademo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HarkkademoApplication

fun main(args: Array<String>) {
	runApplication<HarkkademoApplication>(*args)
}
