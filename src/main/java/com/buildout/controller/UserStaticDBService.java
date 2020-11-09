package com.buildout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.buildout.model.User;
import com.buildout.repository.UserRepository;

/**
 * User service which is responsible to fetch the data from static repository.
 * This is a qualified {@link Bean} which will be invoke in
 * {@link UserController} as per the requested version.
 * 
 * @author Paragraj Kale
 *
 */
@Service("Version1")
public class UserStaticDBService implements BaseUserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	@Override
	public User getUser(@NonNull Integer userId) throws Exception {
		return userRepository.getUser(userId);
	}

	@Override
	public Integer addUser(@NonNull User user) {
		return userRepository.addUser(user);
	}

	@Override
	public boolean deleteUser(@NonNull Integer userId) throws Exception {
		return userRepository.deleteUser(userId);
	}

	@Override
	public boolean updateUser(@NonNull Integer userId, @NonNull User user) throws Exception {
		return userRepository.updateUser(userId, user);
	}
}
