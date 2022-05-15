package com.rr.springbootweb.forms.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("timeElapsedInterceptor")
	private HandlerInterceptor timeElapsedInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//Indica que solo se va a aplicar el interceptor lo que esté dentro de form
		registry.addInterceptor(timeElapsedInterceptor).addPathPatterns("/form/**");
	}
}
