package org.dripto.gradle.client.shout

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@SpringBootTest(classes = [WebClientAutoConfiguration::class, ShoutConfig::class])
internal class ShoutClientTest{
    @Autowired
    lateinit var client: ShoutClient
    @Test
    fun testQuotesClient(): Unit = runBlocking{
        expectThat(client.shout("hello")) isEqualTo "HELLO"
    }
}