package cts.rabobank.glassdoorscheduler.exception;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;
import javax.validation.UnexpectedTypeException;

@ControllerAdvice
public class GlassDoorExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(GlassDoorExceptionHandler.class);

	@ExceptionHandler(value = { UnexpectedTypeException.class})
	public ResponseEntity<CustomMessage> handleUnexpectedTypeException(Exception exception){
		log.debug("Exception: {}",exception.getMessage());
		CustomMessage apiCustomMessage = new CustomMessage(HttpStatus.BAD_REQUEST.value(),"Invalid Input requested");
		//TODO response status code is not clear
		return new ResponseEntity<>(apiCustomMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { InvalidInputRequestException.class})
	public ResponseEntity<CustomMessage> handleInvalidInputRequestException(Exception exception){
		log.debug("Exception: {}",exception.getMessage());
		CustomMessage apiCustomMessage = new CustomMessage(HttpStatus.BAD_REQUEST.value(),exception.getMessage());
		//TODO response status code is not clear
		return new ResponseEntity<>(apiCustomMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<CustomMessage> handleAllExceptions(Exception exception){
		log.debug("Exception: {}",exception.getMessage());
		CustomMessage apiCustomMessage = new CustomMessage(HttpStatus.BAD_REQUEST.value(),"Error :" + exception.getMessage());
		return new ResponseEntity<>(apiCustomMessage, HttpStatus.BAD_REQUEST);
	}
}
