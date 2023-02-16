package com.ndgndg91.byfeature.global.config

import com.ndgndg91.byfeature.global.filter.FirstFilter
import com.ndgndg91.byfeature.global.filter.SecondFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class FilterConfig {

    @Bean
    fun secondFilter(): FilterRegistrationBean<SecondFilter>? {
        val registrationBean: FilterRegistrationBean<SecondFilter> = FilterRegistrationBean<SecondFilter>()
        registrationBean.filter = SecondFilter()
        registrationBean.addUrlPatterns("/user/*")
        registrationBean.order = 2
        registrationBean.setName("second-filter")
        return registrationBean
    }

    @Bean
    fun firstFilter(): FilterRegistrationBean<FirstFilter>? {
        val registrationBean: FilterRegistrationBean<FirstFilter> = FilterRegistrationBean<FirstFilter>()
        registrationBean.filter = FirstFilter()
        registrationBean.addUrlPatterns("/user/*")
        registrationBean.order = 1
        registrationBean.setName("first-filter")
        return registrationBean
    }
}