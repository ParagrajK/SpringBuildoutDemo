package com.buildout.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * The is a custom MethodArgumentResolver which is used to resolve the version
 * from request parameter.
 * 
 * @author Paragraj Kale
 *
 */
@Component
public class APIVersionParameterResolver implements HandlerMethodArgumentResolver {

	private static final String VERSION = "version";

	@Override
	public boolean supportsParameter(@NonNull MethodParameter parameter) {
		return parameter.getParameterAnnotation(APIVersion.class) != null;
	}

	@Override
	public Object resolveArgument(@NonNull MethodParameter parameter, 
			@NonNull ModelAndViewContainer mavContainer,
			@NonNull NativeWebRequest webRequest, 
			@NonNull WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
		String version = httpServletRequest.getParameter(VERSION);

		Version apiVersion = new Version();
		if (StringUtils.isEmpty(version)) {
			APIVersion parameterAnnotation = parameter.getParameterAnnotation(APIVersion.class);
			if (parameterAnnotation != null) {
				apiVersion.setApiVersion(parameterAnnotation.value());
			}
			return apiVersion;
		}
		apiVersion.setApiVersion(Integer.parseInt(version));
		return apiVersion;
	}
}
