package com.letsfame.response;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Response> errorResponse(String errorMessage, HttpStatus httpStatus) {

		Error error = new Error();
		error.setCause(errorMessage.replace("BAD_REQUEST_ERROR:", " "));
		error.setCode(httpStatus.value());
		Response response = new Response();
		response.setError(error);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpStatus);
		return responseEntity;
	}

	public static ResponseEntity<Response> errorResponse(Response response) {
		if (response.getMessage() != null) {
			Error error = new Error();
			error.setCause(response.getMessage().replace("BAD_REQUEST_ERROR:", " "));
			error.setCode(response.getHttpStatus().value());
			response.setError(error);
		}
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, response.getHttpStatus());
		return responseEntity;
	}

	public static ResponseEntity<Response> successGetResponse(String message, Object object, HttpStatus httpStatus) {

		Response response = new Response();
		response.setData(object);
		response.setMessage(message);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpStatus);
		return responseEntity;
	}

	public static ResponseEntity<Response> successGetResponse(Response response) {

		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, response.getHttpStatus());
		return responseEntity;
	}

}
