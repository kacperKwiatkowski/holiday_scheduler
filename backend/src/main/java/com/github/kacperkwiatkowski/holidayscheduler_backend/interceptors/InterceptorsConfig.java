package com.github.kacperkwiatkowski.holidayscheduler_backend.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class InterceptorsConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(
                        new AdminCommandsRestrictionsInterceptor()
                )
                .addPathPatterns("/api/user/update/**")
                .addPathPatterns("/api/user/delete/**")
                .addPathPatterns("/api/vacation/update/**")
                .addPathPatterns("/api/vacation/delete/**");
    }
}