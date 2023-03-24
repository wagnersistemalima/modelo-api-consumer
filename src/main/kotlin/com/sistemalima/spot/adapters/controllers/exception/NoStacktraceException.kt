package com.sistemalima.spot.adapters.controllers.exception

open class NoStacktraceException: RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable, true, false)

}