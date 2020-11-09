package com.buildout.controller;

import java.util.List;

import org.springframework.lang.NonNull;

import com.buildout.model.User;

/**
 * Interface provides an user actions which need to be done by respective
 * implementer class.
 * 
 * @author Paragraj Kale
 *
 */
public interface BaseUserService {

	@NonNull
	public List<User> getUsers();

	@NonNull
	public User getUser(@NonNull Integer userId) throws Exception;

	@NonNull
	public Integer addUser(@NonNull User user);

	public boolean deleteUser(@NonNull Integer userId) throws Exception;

	public boolean updateUser(@NonNull Integer userId, @NonNull User user) throws Exception;
}
