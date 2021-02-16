package com.bhjang.configuration.servlet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bhjang.configuration.exception.BaseException;
import com.bhjang.configuration.http.BaseResponseCode;
import com.bhjang.framework.web.bind.annotation.RequestConfig;


public class BaseHandlerInterceptor implements HandlerInterceptor  {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle requestURI : {}", request.getRequestURI());
        if(handler instanceof HandlerMethod) {
        	HandlerMethod handlerMethod =(HandlerMethod) handler;
        	logger.info("handlerMethod : {}", handlerMethod);
        	RequestConfig requestConfig =handlerMethod.getMethodAnnotation(RequestConfig.class);
        	if(requestConfig != null) {
        		//로그인체크가 필수인 경우 
        		if(requestConfig.loginCheck()) {
        			throw new BaseException(BaseResponseCode.LOGIN_REQUIRE, new String[] {request.getRequestURI()});
        		}
        		
        	}
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        logger.info("postHandle requestURI : {}", request.getRequestURI());
    }
}