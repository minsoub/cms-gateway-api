package com.bithumbsystems.cms.com.bithumbsystems.cms.config

import org.springframework.cloud.gateway.config.GlobalCorsProperties
import org.springframework.cloud.gateway.handler.FilteringWebHandler
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.core.env.Environment
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class PassCorsRoutePredicateHandlerMapping(
    webHandler: FilteringWebHandler,
    routeLocator: RouteLocator,
    globalCorsProperties: GlobalCorsProperties,
    environment: Environment
) : RoutePredicateHandlerMapping(webHandler, routeLocator, globalCorsProperties, environment) {

    override fun getHandler(exchange: ServerWebExchange): Mono<Any> {
        logger.info("[PassCorsRoutePredicateHandlerMapping] getHandler")
        return getHandlerInternal(exchange).map { handler: Any ->
            logger.info(exchange.logPrefix + "Mapped to " + handler)
            handler
        }
    }
}
