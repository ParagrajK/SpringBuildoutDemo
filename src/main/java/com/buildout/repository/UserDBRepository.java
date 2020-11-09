package com.buildout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buildout.model.User;

/**
 * Repository used to perform user DB operations.
 * 
 * @author Paragraj Kale
 *
 */
@Repository
public interface UserDBRepository extends JpaRepository<User, Integer> {

}
