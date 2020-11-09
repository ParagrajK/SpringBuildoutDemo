package com.buildout.exception;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Custom exception which will be thrown from Repository in case DB operation
 * goes wrong.
 * 
 * @author Paragraj Kale
 *
 */
public class UserNotFoundException extends Exception {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = 6422601665281066869L;
	private final Integer userId;

	public UserNotFoundException(@NonNull String message, @Nullable Integer userId) {
		super(message);
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}
}
