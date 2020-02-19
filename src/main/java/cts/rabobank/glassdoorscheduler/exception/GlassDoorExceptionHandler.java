package cts.rabobank.glassdoorscheduler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cts.rabobank.glassdoorscheduler.util.CustomMessage;


@ControllerAdvice
public class GlassDoorExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<?> handleAllExceptions(Exception exception){
		CustomMessage apiCustomMessage = new CustomMessage(HttpStatus.BAD_REQUEST,"Internal Error :" + exception.getMessage());
		return new ResponseEntity<CustomMessage>(apiCustomMessage, HttpStatus.BAD_REQUEST);
	}
}
