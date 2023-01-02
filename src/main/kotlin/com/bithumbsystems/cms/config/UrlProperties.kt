package com.bithumbsystems.cms.com.bithumbsystems.cms.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class UrlProperties(
    @Value("\${sites.cms-url}") val cmsUrl: String
)
