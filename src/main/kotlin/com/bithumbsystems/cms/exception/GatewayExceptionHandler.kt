package com.bithumbsystems.cms.com.bithumbsystems.cms.exception

import com.bithumbsystems.cms.com.bithumbsystems.cms.model.enums.ErrorCode
import com.bithumbsystems.cms.com.bithumbsystems.cms.model.response.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class GatewayExceptionHandler : ErrorWebExceptionHandler {
    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        val errorData: ErrorData
        var errorCode = -1
        val errorMessage: String

        if (ex.javaClass.name == GatewayException::javaClass.name) {
            val e = ex as GatewayException
            errorCode = ErrorCode.valueOf(e.message!!).code
            errorMessage = ErrorCode.valueOf(e.message!!).message
            errorData = ErrorData(errorCode, errorMessage)
        } else {
            throw GatewayException(ErrorCode.GATEWAY_SERVER_ERROR)
        }

        val objectMapper = ObjectMapper()
        val bytes = (objectMapper.writeValueAsString(ErrorResponse(errorData))).toByteArray(Charsets.UTF_8)

        val buffer = exchange.response.bufferFactory().wrap(bytes)

        // if (ex.javaClass.name == GatewayException)

        return exchange.response.writeWith(Flux.just(buffer))
    }
}
