package com.buildout.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Entity which is used to map with DB table.
 * 
 * @author Paragraj Kale
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Nullable
	private Integer id;
	@Size(min = 2, message = "Username should be greater than 2 characters")
	private String username;
	@Nullable
	private String occupation;

	public User() {
		super();
	}

	public User(@NonNull Integer id, @NonNull String username, @Nullable String occupation) {
		super();
		this.id = id;
		this.username = username;
		this.occupation = occupation;
	}

	@Nullable
	public Integer getId() {
		return id;
	}

	public void setId(@Nullable Integer id) {
		this.id = id;
	}

	@NonNull
	public String getUsername() {
		return username;
	}

	public void setUsername(@NonNull String username) {
		this.username = username;
	}

	public String getOccupation() {
		return occupation;
	}

	@Nullable
	public void setOccupation(@Nullable String occupation) {
		this.occupation = occupation;
	}
}
