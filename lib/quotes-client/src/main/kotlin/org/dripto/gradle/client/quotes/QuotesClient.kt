package org.dripto.gradle.client.quotes

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

class QuotesClient(
    builder: WebClient.Builder
) {
    private val webclient = builder.baseUrl("https://api.quotable.io")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    suspend fun randomQuote() = webclient.get().uri("/random")
        .retrieve().bodyToMono<Response>().awaitSingle().content
}

data class Response(val content: String)

@Configuration
class QuotesConfig{
    @Bean
    fun quotesClient(builder: WebClient.Builder) = QuotesClient(builder)
}