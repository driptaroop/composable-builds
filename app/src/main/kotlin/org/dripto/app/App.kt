package org.dripto.app

import kotlinx.coroutines.runBlocking
import org.dripto.shout.quotes.ShoutQuotes
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(ShoutQuotes::class)
class App {
    @Bean
    fun runner(quote: ShoutQuotes) = ApplicationRunner {
        runBlocking {
            println(quote.shoutQuote())
        }
    }
}

fun main(args: Array<String>) {
    runApplication<App>(*args) {
        webApplicationType = WebApplicationType.NONE
    }
}