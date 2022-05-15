package com.rr.springbootweb.forms.app.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("timeElapsedInterceptor")
public class TimeElapsedInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TimeElapsedInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//Solo aplica el interceptor cuando la petición viene por Get y no por Post
		if(request.getMethod().equalsIgnoreCase("post")) {
			return true;
		}
		
		if (handler instanceof HandlerMethod) {
			//Aquí accedemos al handler y podemos imprimir cualquier dato que este maneje
			HandlerMethod method = (HandlerMethod) handler;
			logger.info("This is a controller method " + method.getMethod().getName());
		}
		
		logger.info("TimeElapsedInterceptor preHandle() : loading...");
		
		//Esto sirve para saber qué es lo que está interceptando y si da error poder verlo
		logger.info("Intercepting handler: " + handler);
		long initialTime = System.currentTimeMillis();
		request.setAttribute("initialTime", initialTime);
		Random random = new Random();
		int delay = random.nextInt(100);
		Thread.sleep(delay);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		//Solo aplica el interceptor cuando la petición viene por Get y no por Post
		if(request.getMethod().equalsIgnoreCase("post")) {
			return;
		}
		
		
		long endTime = System.currentTimeMillis();
		long initialTime = (Long) request.getAttribute("initialTime");
		long timeElapsed = endTime - initialTime;

		//Solo con la validación del handler ya bastaría, pero si queremos ser más estrictos
		//podemos validar también el modelAndView
		if (handler instanceof HandlerMethod && modelAndView != null) {
			modelAndView.addObject("timeElapsed", timeElapsed);
		}

		logger.info("Time elapsed: " + timeElapsed + " milliseconds");
		logger.info("TimeElapsedInterceptor postHandle() : loading...");
	}
}
