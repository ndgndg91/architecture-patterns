package com.ndgndg91.ndgndg91architecturepatterns

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ByLayerApplication

fun main(args: Array<String>) {
    runApplication<ByLayerApplication>(*args)
}
