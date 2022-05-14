package com.rr.springbootweb.forms.app.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TimeElapsedInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TimeElapsedInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("TimeElapsedInterceptor preHandle() : loading...");
		long initialTime = System.currentTimeMillis();
		request.setAttribute("initialTime", initialTime);
		Random random = new Random();
		int delay = random.nextInt(500);
		Thread.sleep(delay);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long endTime = System.currentTimeMillis();
		long initialTime = (Long) request.getAttribute("initialTime");
		long elapsedTime = endTime - initialTime;

		if (modelAndView != null) {
			modelAndView.addObject("elapsedTime", elapsedTime);
		}

		logger.info("Elapsed time: " + elapsedTime + " miliseconds");
		logger.info("TimeElapsedInterceptor postHandle() : loading...");
	}
}
