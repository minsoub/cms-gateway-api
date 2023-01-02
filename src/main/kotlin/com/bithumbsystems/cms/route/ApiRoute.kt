package com.bithumbsystems.cms.com.bithumbsystems.cms.route

import com.bithumbsystems.cms.com.bithumbsystems.cms.config.Config
import com.bithumbsystems.cms.com.bithumbsystems.cms.config.UrlProperties
import com.bithumbsystems.cms.com.bithumbsystems.cms.filters.ApiFilters
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiRoute(
    var urlProperties: UrlProperties,
    var apiFilters: ApiFilters
) {
    @Bean
    fun apiRouteLocator(builder: RouteLocatorBuilder) = builder.routes {
        route(id = "cms-api-user") {
            path("/api/*/cms/**")
            filters { apiFilters.apply(Config("CMS User ApiFilter apply", true, true)) }
            uri(urlProperties.cmsUrl)
        }
    }
}
