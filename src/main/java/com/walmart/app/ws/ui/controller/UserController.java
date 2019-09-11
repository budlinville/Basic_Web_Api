package com.walmart.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.walmart.app.ws.exceptions.UserServiceException;
import com.walmart.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.walmart.app.ws.ui.model.request.UserDetilsRequestModel;
import com.walmart.app.ws.ui.model.response.UserRest;
import com.walmart.app.ws.userservice.UserService;

@RestController
@RequestMapping("users")	// http://localhost:8080/users/
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping()
	public ResponseEntity<ArrayList<UserRest>> getUsers(@RequestParam(value="page", defaultValue="1") Integer page,
			@RequestParam(value="limit", defaultValue="50") Integer limit,
			@RequestParam(value="sort", defaultValue="desc", required=false) String sort) {
		return new ResponseEntity<ArrayList<UserRest>>(userService.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping( path="/{userId}", produces= {
		MediaType.APPLICATION_XHTML_XML_VALUE, 
		MediaType.APPLICATION_JSON_VALUE
	} )
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		UserRest usr = userService.getUser(userId);
		if (usr != null) {
			return new ResponseEntity<UserRest>(usr, HttpStatus.OK);
		}
		
		return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping( consumes={
		MediaType.APPLICATION_XHTML_XML_VALUE, 
		MediaType.APPLICATION_JSON_VALUE
	}, produces={
		MediaType.APPLICATION_XHTML_XML_VALUE, 
		MediaType.APPLICATION_JSON_VALUE
	} )
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetilsRequestModel userDetails) {
		UserRest retVal = userService.createUser(userDetails);
		return new ResponseEntity<UserRest>(retVal, HttpStatus.OK);
	}
	
	@PutMapping( path="/{userId}", consumes={
		MediaType.APPLICATION_XHTML_XML_VALUE,
		MediaType.APPLICATION_JSON_VALUE
	}, produces={
		MediaType.APPLICATION_XHTML_XML_VALUE, 
		MediaType.APPLICATION_JSON_VALUE
	} )
	public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
		UserRest updatedUser = userService.updateUser(userId, userDetails);
		
		if (updatedUser != null) {
			return new ResponseEntity<UserRest>(updatedUser, HttpStatus.OK);
		}
		
		return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
}
