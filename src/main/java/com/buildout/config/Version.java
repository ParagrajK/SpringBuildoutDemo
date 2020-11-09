package com.buildout.config;

import org.springframework.lang.NonNull;

/**
 * The POJO is used to keep API version information which will be used in
 * controller to fetch the data accordingly.
 * 
 * @author Paragraj Kale
 *
 */
public class Version {

	@NonNull
	Integer apiVersion;

	public Version() {
		super();
	}

	@NonNull
	public Integer getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(@NonNull Integer apiVersion) {
		this.apiVersion = apiVersion;
	}

	@Override
	public String toString() {
		return Version.class.getSimpleName() + apiVersion;
	}

}
