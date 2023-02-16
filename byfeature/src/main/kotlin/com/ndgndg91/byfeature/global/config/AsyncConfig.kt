package com.ndgndg91.byfeature.global.config

import org.slf4j.LoggerFactory
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor


@EnableAsync
@Configuration
class AsyncConfig: AsyncConfigurer {
    private val log = LoggerFactory.getLogger(AsyncConfig::class.java)

    override fun getAsyncExecutor(): Executor? {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 7
        executor.maxPoolSize = 42
        executor.queueCapacity = 11
        executor.setThreadNamePrefix("MyExecutor-")
        executor.initialize()
        return executor
    }

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler? {
        return AsyncUncaughtExceptionHandler { ex, method, params ->
            log.error("Method name : {}, param Count : {}\n\nException Cause -{}", method.name, params.size, ex.message)
        }
    }
}