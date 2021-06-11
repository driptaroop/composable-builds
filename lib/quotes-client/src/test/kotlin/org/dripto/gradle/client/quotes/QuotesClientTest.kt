package org.dripto.gradle.client.quotes

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import strikt.api.expectThat
import strikt.assertions.hasLength
import strikt.assertions.isNotEmpty

@SpringBootTest(classes = [WebClientAutoConfiguration::class, QuotesConfig::class])
internal class QuotesClientTest {
    @Autowired lateinit var client: QuotesClient
    @Test
    fun testQuotesClient(): Unit = runBlocking{
        expectThat(client.randomQuote()).isNotEmpty()
    }
}