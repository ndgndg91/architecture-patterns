package com.ndgndg91.byfeature.user.service.dto.event

import org.springframework.context.ApplicationEvent

class SignUpEvent(source: Any, val email: String): ApplicationEvent(source) {

}