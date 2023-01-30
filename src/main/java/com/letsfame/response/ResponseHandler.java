package com.letsfame.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

//	public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj, String message) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("message", message);
//		map.put("status", status.value());
//		map.put("data", responseObj);
//		return new ResponseEntity<Object>(map, status);
//	}
	public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj) {
		// In case Handle some more data in future
		return new ResponseEntity<Object>(responseObj, status);
	}
	public static ResponseEntity<Object> generateResponse(HttpStatus status, List<Object>responseObj) {
		// In case Handle some more data in future
		return new ResponseEntity<Object>(responseObj, status);
	}

	public static ResponseEntity<Response> errorResponse(String errorMessage, HttpStatus httpStatus) {
		Error error = new Error();
		error.setReason(errorMessage);
		Response response = new Response();
		response.setError(error);
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpStatus);
		return responseEntity;
	}
}
