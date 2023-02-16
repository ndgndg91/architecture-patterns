package com.ndgndg91.byfeature.user.service

import com.ndgndg91.byfeature.client.EmailClient
import com.ndgndg91.byfeature.user.service.dto.event.SignUpEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class UserEventHandler(
    private val client: EmailClient
) {

    @Async
    @EventListener(SignUpEvent::class)
    fun signUp(event: SignUpEvent) {
        client.send(event.email, "")
    }
}