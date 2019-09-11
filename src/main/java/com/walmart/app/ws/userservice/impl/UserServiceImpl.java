package com.walmart.app.ws.userservice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.walmart.app.ws.shared.Utils;
import com.walmart.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.walmart.app.ws.ui.model.request.UserDetilsRequestModel;
import com.walmart.app.ws.ui.model.response.UserRest;
import com.walmart.app.ws.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {
	Map<String, UserRest> users;
	Utils utils;
	
	public UserServiceImpl() {}
	
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}
	
	@Override
	public UserRest createUser(UserDetilsRequestModel userDetails) {
		UserRest retVal = new UserRest();
		String userId = utils.generateUserId();
		
		retVal.setEmail(userDetails.getEmail());
		retVal.setFirstName(userDetails.getFirstName());
		retVal.setLastName(userDetails.getLastName());
		retVal.setUserId(userId);
		
		if (users == null) users = new HashMap<>();
		users.put(userId, retVal);
		
		return retVal;
	}
	
	@Override
	public UserRest getUser(String userId) {
		if (users.containsKey(userId)) {
			return users.get(userId);
		}
		
		return null;
	}
	
	@Override
	public UserRest updateUser(String userId, UpdateUserDetailsRequestModel userDetails) {
		if (users.containsKey(userId)) {
			UserRest storedUserDetails = users.get(userId);
			storedUserDetails.setFirstName(userDetails.getFirstName());
			storedUserDetails.setLastName(userDetails.getLastName());
			users.put(userId, storedUserDetails);
			return storedUserDetails;
		}
		
		return null;
	}
	
	@Override
	public void deleteUser(String userId) {
		users.remove(userId);
	}
	
	@Override
	public ArrayList<UserRest> getUsers() {
		return new ArrayList<UserRest>(users.values());
	}
}
