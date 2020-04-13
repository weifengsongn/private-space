package com.swf.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: private-space
 * @description:
 * @author: Wfsong
 * @create: 2020-04-03 20:22
 **/
@Component
public class WebMvcComponent implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index.html").setViewName("index");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/main").setViewName("main");
		registry.addViewController("/test").setViewName("/public/navbar");
//		registry.addViewController("/main").setViewName("main");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
					.excludePathPatterns("/index","/")  //初始页面，不拦截；
					.excludePathPatterns("/user/login") //登录请求不拦截
					.excludePathPatterns("/webjars/**","/css/**");  // 静态资源不拦截
	}
}
