package com.caforerof.calculator.logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class CyxteraLoggerInterceptorAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(cyxteraLoggerInterceptor()).addPathPatterns("/calculatorApi/**");
    }
	
    @Bean
    public CyxteraLoggerInterceptor cyxteraLoggerInterceptor() {
        return new CyxteraLoggerInterceptor();
    }
    
    
}
