package com.example.restfulwebservice;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice  // 사전에 이게 실행 되도록 오류가나면
public class CustomizedResponseEntitiyExceptionHandler extends ResponseEntityExceptionHandler {
	
     @ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex,WebRequest request){
		ExceptionResponse exceptionResponse = 
				  new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
     @ExceptionHandler(UserNotFoundException.class) // UserNotFoundException오류가 나면 이걸로 하는거
 	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex,WebRequest request){
 		ExceptionResponse exceptionResponse = 
 				  new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
 		
 		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
 		
 	}
}
