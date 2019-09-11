package com.walmart.app.ws.userservice;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.walmart.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.walmart.app.ws.ui.model.request.UserDetilsRequestModel;
import com.walmart.app.ws.ui.model.response.UserRest;

@Service
public interface UserService {
	UserRest createUser(UserDetilsRequestModel userDetails);
	
	UserRest getUser(String userId);
	
	UserRest updateUser(String userId, UpdateUserDetailsRequestModel userDetails);
	
	void deleteUser(String userId);
	
	ArrayList<UserRest> getUsers();
}
