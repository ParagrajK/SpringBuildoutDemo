package com.buildout.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buildout.config.APIVersion;
import com.buildout.config.Version;
import com.buildout.model.User;

/**
 * Use controller which is responsible to handle REST request/response 
 * for registered mappings.
 * 
 * @author Paragraj Kale
 *
 */
@RestController
public class UserController {

	@Autowired
	Map<String, BaseUserService> baseUserControllerImpl;

	/**
	 * Test end point to get started with Spring Application.
	 */
	@GetMapping("hello-world")
	public String helloWorld(@APIVersion Version apiVersion) {
		return "Hello World " + apiVersion.getApiVersion();
	}

	@NonNull
	@GetMapping("/users")
	public List<User> getUsers(@APIVersion Version apiVersion) {
		return getUserController(apiVersion).getUsers();
	}

	@NonNull
	@GetMapping("/users/{id}")
	public User getUser(@APIVersion Version apiVersion, 
			@PathVariable(value = "id") Integer userId) throws Exception {
		return getUserController(apiVersion).getUser(userId);
	}

	@NonNull
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@APIVersion Version apiVersionn, 
			@Valid @RequestBody User user,
			@NonNull HttpServletRequest httpServletRequest) {
		Integer userId = getUserController(apiVersionn).addUser(user);
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromRequest(httpServletRequest);
		URI locationUri = builder.path("/users/{id}").buildAndExpand(userId).toUri();
		return ResponseEntity.created(locationUri).build();
	}

	@NonNull
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUer(@APIVersion Version apiVersion, 
			@PathVariable(value = "id") Integer userId)
			throws Exception {
		boolean hasUserDeleted = getUserController(apiVersion).deleteUser(userId);
		if (hasUserDeleted) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.unprocessableEntity().build();
	}

	@NonNull
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@APIVersion Version apiVersion, 
			@PathVariable(value = "id") Integer userId,
			@RequestBody @NonNull User user) throws Exception {
		boolean hasUserUpdated = getUserController(apiVersion).updateUser(userId, user);
		if (hasUserUpdated) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.unprocessableEntity().build();
	}

	@NonNull
	private BaseUserService getUserController(@NonNull Version version) {
		return baseUserControllerImpl.get(version.toString());
	}
}
