package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.DataAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo uRepo;

	@Override
	public User addUser(User user) {
		if ( user != null ) {
			if ( uRepo.usernameAlreadyExists(user.getUsername()) != null )
				throw new DataAlreadyExistsException("Username is taken!");
			return uRepo.saveAndFlush(user);
		}
		return null;
	}

	@Override
	public boolean LoginUser(String username, String password) {
		User user= uRepo.validateUser(username, password);
		if ( user != null)
			return true;
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = uRepo.findAll();
		if ( userList == null || userList.isEmpty())
			return null;
		return userList;
	}

	@Override
	public boolean resetPassword(String password, String username, String answer) {
		uRepo.updatePassword(password, username, answer);
		return LoginUser(username, password);
	}
	
}
