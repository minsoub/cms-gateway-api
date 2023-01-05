package com.bithumbsystems.cms.com.bithumbsystems.cms.route

import com.bithumbsystems.cms.com.bithumbsystems.cms.config.Config
import com.bithumbsystems.cms.com.bithumbsystems.cms.config.PassCorsRoutePredicateHandlerMapping
import com.bithumbsystems.cms.com.bithumbsystems.cms.config.UrlProperties
import com.bithumbsystems.cms.com.bithumbsystems.cms.filters.ApiFilters
import org.springframework.cloud.gateway.config.GlobalCorsProperties
import org.springframework.cloud.gateway.handler.FilteringWebHandler
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.env.Environment

@Configuration
class ApiRoute(
    var urlProperties: UrlProperties,
    var apiFilters: ApiFilters
) {

    @Bean
    @Primary
    fun passCorsRoutePredicateHandlerMapping(
        webHandler: FilteringWebHandler,
        routeLocator: RouteLocator,
        globalCorsProperties: GlobalCorsProperties,
        environment: Environment
    ): RoutePredicateHandlerMapping {
        return PassCorsRoutePredicateHandlerMapping(
            webHandler,
            routeLocator,
            globalCorsProperties,
            environment
        )
    }

    @Bean
    fun apiRouteLocator(builder: RouteLocatorBuilder) = builder.routes {
        route(id = "cms-api-user") {
            path("/api/*/cms/**")
            filters {
                apiFilters.apply(
                    Config(
                        "CMS User ApiFilter apply",
                        preLogger = true,
                        postLogger = true
                    )
                )
            }
            uri(urlProperties.cmsUrl)
        }
    }
}
