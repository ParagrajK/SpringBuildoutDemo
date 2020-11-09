package com.buildout.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The WebMvcConfigurer which is used to register the custom
 * MethodArgumentResolver i.e {@link APIVersionParameterResolver}. The
 * APIVersionParameterResolver is need to be registered to work correctly.
 * 
 * @author Paragraj Kale
 *
 */
@Configuration
public class APIVersionResolverConfiguration implements WebMvcConfigurer {

	@Autowired
	APIVersionParameterResolver apiVersionParameterResolver;

	@Override
	public void addArgumentResolvers(@NonNull List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(apiVersionParameterResolver);
	}
}
