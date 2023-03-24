package com.sistemalima.spot

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.sistemalima.spot")
		.start()
}

