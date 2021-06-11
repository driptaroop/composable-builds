package org.dripto.shout.quotes

import org.dripto.gradle.client.quotes.QuotesClient
import org.dripto.gradle.client.quotes.QuotesConfig
import org.dripto.gradle.client.shout.ShoutClient
import org.dripto.gradle.client.shout.ShoutConfig
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Component

@Import(ShoutConfig::class, QuotesConfig::class)
@Component
class ShoutQuotes(
    private val client: ShoutClient, private val quotesClient: QuotesClient
) {
    suspend fun shoutQuote(): String = client.shout(quotesClient.randomQuote())
}