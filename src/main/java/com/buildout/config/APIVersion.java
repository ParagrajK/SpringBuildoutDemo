package com.buildout.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation which is used to process version from request parameter. The
 * resolved parameter is in the form of {@link Version}.
 * 
 * <p>
 * For example, the annotation can only be used for method parameter type
 * declarations:
 * 
 * <pre>
 *  public void methodName(&#064;APIVersion Version version) {
 *  ....
 *  }
 * 
 * </pre>
 * </p>
 * 
 * @author Paragraj Kale
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface APIVersion {

	/**
	 * The value indicates the version which need to be used. The default value is
	 * one if not specified.
	 * 
	 * @return the API version number.
	 */
	int value() default 1;
}
