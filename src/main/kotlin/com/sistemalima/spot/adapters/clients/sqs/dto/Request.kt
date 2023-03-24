package com.sistemalima.spot.adapters.clients.sqs.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Request<T>(
    @JsonProperty("request")
    val data: T
)
