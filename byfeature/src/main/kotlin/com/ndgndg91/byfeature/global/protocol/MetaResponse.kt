package com.ndgndg91.byfeature.global.protocol

import java.math.BigInteger
import java.time.LocalDateTime
import java.time.ZoneOffset

data class MetaResponse(
    val responseCode: Int,
    val timestamp: BigInteger = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toBigInteger()
)