package com.cssl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Component
public class WebappConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptor hi = new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				if(request.getSession().getAttribute("admin")!=null) {
					
					return true;
				}else {
					response.sendRedirect("/templates/page/login.html");
					return false;
				}
			}
		};
		registry.addInterceptor(hi).addPathPatterns("/templates/htpage/*").excludePathPatterns("/templates/htpage/image/*","/templates/htpage/js/*","/templates/htpage/static/*");
	}
	
}
