package com.arslan_aziz.food_for_thought.application.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;

import com.arslan_aziz.food_for_thought.application.NlpExtractionController;

@Component
public class RateLimitFilter implements Filter {
	
	private Logger logger = LoggerFactory.getLogger(NlpExtractionController.class);
	private final RateLimiter rateLimiter;
	
	public RateLimitFilter() {
		rateLimiter = RateLimiter.create(5);
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        
        String sessionId = session.getId();
        
        if (rateLimiter.tryAcquire() == true) {
        	logger.info("Session " + sessionId + " is within request rate.");
        	chain.doFilter(request, response);
        }
        else {
        	logger.error("Request rate exceeded.");	
        }
	}

}
