package com.bithumbsystems.cms.com.bithumbsystems.cms.model.enums

enum class ErrorCode(
    val code: Int,
    val message: String
) {
    UNKNOWN_ERROR(999, "알 수 없는 에러가 발생하였습니다. 운영자에게 문의 주시기 바랍니다!!!"),
    INVALID_HEADER_USER_IP(901, "Header 정보가 유효하지 않습니다!!(Not found user_ip)"),
    INVALID_HEADER_SITE_ID(902, "Header 정보가 유효하지 않습니다!!(Not found site_id)"),
    INVALID_HEADER_TOKEN(903, "Token 정보가 잘 못되었습니다!!!"),
    EXPIRED_TOKEN(909, "Token expired"),
    SERVER_RESPONSE_ERROR(904, "API 서버에서 에러가 발생하였습니다!!!"),
    GATEWAY_SERVER_ERROR(905, "Gateway Server Error"),
    USER_ALREADY_LOGIN(906, "User is already login"),
    AUTH_SERVER_RESPONSE_ERROR(905, "Auth server error."),
    AUTH_SERVER_AUTHORIZATION_FAIL(906, "Authorization fail."),
    AUTHORIZATION_FAIL(907, "Fail Authorization");
}
