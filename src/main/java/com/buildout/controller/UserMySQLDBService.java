package com.buildout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.buildout.model.User;
import com.buildout.repository.UserDBRepository;

/**
 * User service which is responsible to fetch the data from MySQL Database. This
 * is a qualified {@link Bean} which will be invoke in {@link UserController} as
 * per the requested version.
 * 
 * @author Paragraj Kale
 *
 */
@Service("Version2")
public class UserMySQLDBService implements BaseUserService {

	@Autowired
	UserDBRepository userDBrepository;

	@Override
	public List<User> getUsers() {
		return userDBrepository.findAll();
	}

	@Override
	public User getUser(@NonNull Integer userId) throws Exception {
		return userDBrepository.findById(userId).orElseThrow();
	}

	@Override
	public Integer addUser(@NonNull User user) {
		return userDBrepository.save(user).getId();
	}

	@Override
	public boolean deleteUser(@NonNull Integer userId) throws Exception {
		userDBrepository.deleteById(userId);
		return true;
	}

	@Override
	public boolean updateUser(@NonNull Integer userId, @NonNull User user) throws Exception {
		user.setId(userId);
		return userDBrepository.save(user).getId() != null;
	}

}
