package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;

import jakarta.transaction.Transactional;

@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {
	
	@Query(value="select u from User u where u.username= :username and u.password= :password")
	public User validateUser(String username, String password);
	
	@Modifying
	@Query("update User u set u.password= :password where u.username= :username and u.answer= :answer")
	public void updatePassword(String password, String username, String answer);
	
	@Query("select u.username from User u where u.username=:username")
	public String usernameAlreadyExists(String username);

}