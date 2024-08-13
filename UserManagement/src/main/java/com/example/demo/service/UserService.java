package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

	public User addUser(User user);
	
	public boolean LoginUser(String username, String password);
	
	public List<User> getAllUsers();
	
	public boolean resetPassword(String newPassword, String username, String answer);
}
