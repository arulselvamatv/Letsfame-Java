package com.letsfame.response;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());
		map.put("data", responseObj);
		return new ResponseEntity<Object>(map, status);
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
