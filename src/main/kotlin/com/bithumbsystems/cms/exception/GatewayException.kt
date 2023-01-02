package com.bithumbsystems.cms.com.bithumbsystems.cms.exception

import com.bithumbsystems.cms.com.bithumbsystems.cms.model.enums.ErrorCode

class GatewayException(private var errorCode: ErrorCode) : RuntimeException(errorCode.toString())
