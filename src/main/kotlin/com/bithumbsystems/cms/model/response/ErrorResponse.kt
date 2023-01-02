package com.bithumbsystems.cms.com.bithumbsystems.cms.model.response

import com.bithumbsystems.cms.com.bithumbsystems.cms.exception.ErrorData

class ErrorResponse(private var error: ErrorData, private var result: String, var data: String) {

    constructor(errorData: ErrorData) : this(errorData, String(), String()) {
        error = errorData
        result = "FAIL"
        data = String()
    }

    @Override
    override fun toString(): String {
        return "{\"result\":\"" + result + "\", \"error\": { \"code\":" + error.code + ", \"message\":\"" + error.message + "\"}, \"data\": null}"
    }
}
