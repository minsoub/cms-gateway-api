package com.bithumbsystems.cms.com.bithumbsystems.cms.filters

import com.bithumbsystems.cms.com.bithumbsystems.cms.config.Config
import com.bithumbsystems.cms.com.bithumbsystems.cms.exception.GatewayExceptionHandler
import com.bithumbsystems.cms.com.bithumbsystems.cms.utils.Logger
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ApiFilters : AbstractGatewayFilterFactory<Config>() {

    private val logger by Logger()

    @Bean
    fun exceptionHandler(): ErrorWebExceptionHandler {
        return GatewayExceptionHandler()
    }

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val url = exchange.request.uri.path
            if (config.preLogger) {
                logger.info("ApiFilter Start: {}", exchange.request.id)
            }
            logger.info(url)
            chain.filter(exchange).then(
                Mono.fromRunnable {
                    if (config.postLogger) {
                        logger.info("ApiFilter End: {}", exchange.request.id)
                    }
                }
            )
        }
    }
}
