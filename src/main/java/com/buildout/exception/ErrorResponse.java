package com.buildout.exception;

import java.util.Date;

import org.springframework.lang.NonNull;

/**
 * Entity which is used to send error details to user in the form of response.
 * 
 * @author Paragraj Kale
 *
 */
public class ErrorResponse {

	@NonNull
	private String message;
	@NonNull
	private Date date;

	public ErrorResponse(@NonNull String message, @NonNull Date date) {
		super();
		this.message = message;
		this.date = date;
	}

	@NonNull
	public String getMessage() {
		return message;
	}

	public void setMessage(@NonNull String message) {
		this.message = message;
	}

	@NonNull
	public Date getDate() {
		return date;
	}

	public void setDate(@NonNull Date date) {
		this.date = date;
	}

}
