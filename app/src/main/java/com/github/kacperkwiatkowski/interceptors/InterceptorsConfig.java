package com.github.kacperkwiatkowski.interceptors;

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
                .addPathPatterns("/api/com.github.kacperkwiatkowski.user/update/**")
                .addPathPatterns("/api/com.github.kacperkwiatkowski.user/delete/**")
                .addPathPatterns("/api/com.github.kacperkwiatkowski.vacation/update/**")
                .addPathPatterns("/api/com.github.kacperkwiatkowski.vacation/delete/**");
    }
}