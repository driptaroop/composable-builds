package org.dripto.gradle.client.shout

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

class ShoutClient(
    builder: WebClient.Builder
){
    private val webclient = builder.baseUrl("http://api.shoutcloud.io/V1")
        .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
        .build()

    suspend fun shout(value: String): String =
        webclient.post().uri("/SHOUT")
            .bodyValue(ShoutRequest(value))
            .retrieve().bodyToMono<Shout>()
            .awaitSingle().output
}

data class ShoutRequest(@JsonProperty("INPUT") val input: String)

data class Shout(
    @JsonProperty("OUTPUT")
    val output: String
)

@Configuration
class ShoutConfig{
    @Bean
    fun shoutClient(builder: WebClient.Builder) = ShoutClient(builder)
}