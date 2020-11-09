package com.buildout.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.buildout.exception.UserNotFoundException;
import com.buildout.model.User;

/**
 * Repository used to perform user operations on static list of users.
 * 
 * @author Paragraj Kale
 *
 */
@Repository
public class UserRepository {

	private static final List<User> USERS = new ArrayList<>();
	private static int USER_COUNTER = 4;

	static {
		USERS.add(new User(1, "Rahil", "Software Engineer"));
		USERS.add(new User(2, "Akash", "Software Developer"));
		USERS.add(new User(3, "Sonam", null));
		USERS.add(new User(4, "Javed", "System Analyst"));
	}

	@NonNull
	public List<User> getUsers() {
		return USERS;
	}

	@Nullable
	public User getUser(@Nullable Integer id) throws Exception {
		if (id == null) {
			throw new Exception("User id should not be null");
		}
		for (User user : USERS) {
			if (id == user.getId()) {
				return user;
			}
		}
		throw new UserNotFoundException("User not found in the record", id);
	}

	@NonNull
	public Integer addUser(@NonNull User user) {
		user.setId(++USER_COUNTER);
		USERS.add(user);
		return user.getId();
	}

	@NonNull
	public Boolean updateUser(@Nullable Integer id, @NonNull User updatedUser) throws Exception {
		if (id == null) {
			throw new Exception("User id should not be null");
		}
		for (User user : USERS) {
			if (id == user.getId()) {
				user.setOccupation(updatedUser.getOccupation());
				user.setUsername(updatedUser.getUsername());
				return true;
			}
		}
		throw new UserNotFoundException("User not found in the record", id);
	}

	@NonNull
	public Boolean deleteUser(@Nullable Integer id) throws Exception {
		if (id == null) {
			throw new Exception("User id should not be null");
		}
		Iterator<User> iterator = USERS.iterator();
		while (iterator.hasNext()) {
			User user = (User) iterator.next();
			if (id == user.getId()) {
				iterator.remove();
				return true;
			}
		}
		throw new UserNotFoundException("User not found in the record", id);
	}
}
