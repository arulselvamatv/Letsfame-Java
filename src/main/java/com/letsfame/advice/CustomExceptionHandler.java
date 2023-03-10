package com.letsfame.advice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.letsfame.response.Error;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.letsfame.response.Response;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("Handling handleMethodArgumentNotValid Exception");
		List<String> errorlist = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> (x.getField() + " : " + x.getDefaultMessage())).collect(Collectors.toList());

		Response response = new Response();
		response.setData("Validation Failed");
		Error err = new Error();
		err.setCode(HttpStatus.BAD_REQUEST.toString());
		err.setReason("Validation Failed");
		err.setErrorList(errorlist);
		response.setError(err);
		List<String> errors = new ArrayList<>();
		BindingResult bindingResult = ex.getBindingResult();
		bindingResult.getAllErrors().forEach(error -> errors.add(error.getCode()));
		response.setErrorMessages(errors);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ObjectInvalidException.class)
	public ResponseEntity<?> handleInvalidObjectException(Exception ex, WebRequest request) {
		log.info("Handling handleInvalidObjectException Exception");
		Error errors = new Error();
		errors.setErrorList((Stream.of(ex.getMessage().split(",")).collect(Collectors.toList())));
		errors.setReason(ex.getMessage());
		errors.setCode(HttpStatus.BAD_REQUEST.toString());
		Response response = new Response();
		response.setError(errors);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		log.info("Exception:" + ExceptionUtils.getStackTrace(ex));

		Error errors = new Error();
		List<String> errorList = new ArrayList<>();
		errors.setReason("Invalid Json Data");
		if (ex.getMessage().contains("java.time.LocalDate")) {
			errorList.add("Invalid Date format / Invalid Date value");
		}
		errorList.addAll(Stream.of(ex.getMessage().split(",")).collect(Collectors.toList()));
		errors.setCode(HttpStatus.BAD_REQUEST.toString());
		errors.setErrorList(errorList);
		Response response = new Response();
		response.setError(errors);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
