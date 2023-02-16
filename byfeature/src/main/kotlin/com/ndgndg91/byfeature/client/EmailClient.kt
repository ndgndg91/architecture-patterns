package com.ndgndg91.byfeature.client

import org.springframework.stereotype.Component

@Component
class EmailClient {

    fun send(toEmail: String, content: String): Boolean {
        return true
    }
}