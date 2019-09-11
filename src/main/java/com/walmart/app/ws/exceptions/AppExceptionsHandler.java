package com.walmart.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.walmart.app.ws.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
	/*********************************************************
	 * General Exception
	 *********************************************************/
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {	//ex = entire exception stack trace
		String errMsgDesc = ex.getLocalizedMessage();
		
		if (errMsgDesc == null) errMsgDesc = ex.toString();
		
		ErrorMessage errMsg = new ErrorMessage(new Date(), errMsgDesc);
		
		return new ResponseEntity<>(
			errMsg, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
	
	/*********************************************************
	 * Null Pointer Exception & Custom User Service Exception
	 *********************************************************/
	@ExceptionHandler(value = { NullPointerException.class, UserServiceException.class })
	public ResponseEntity<Object> handleNullPointerException(Exception ex, WebRequest request) {	//ex = entire exception stack trace
		String errMsgDesc = ex.getLocalizedMessage();
		
		if (errMsgDesc == null) errMsgDesc = ex.toString();
		
		ErrorMessage errMsg = new ErrorMessage(new Date(), errMsgDesc);
		
		return new ResponseEntity<>(
			errMsg, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
	
	/*********************************************************
	 * Custom User Service exception (redundant)
	 *********************************************************/
	/*
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {	//ex = entire exception stack trace
		String errMsgDesc = ex.getLocalizedMessage();
		
		if (errMsgDesc == null) errMsgDesc = ex.toString();
		
		ErrorMessage errMsg = new ErrorMessage(new Date(), errMsgDesc);
		
		return new ResponseEntity<>(
			errMsg, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
	*/
}
