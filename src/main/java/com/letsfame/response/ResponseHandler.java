package com.letsfame.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

//	private static final Logger logger = Logger.getLogger(ResponseHandler.class);
	
	public static ResponseEntity<Response> errorResponse(String errorMessage, HttpStatus httpStatus) {
		Error error = new Error();
		error.setReason(errorMessage.replace("BAD_REQUEST_ERROR:"," "));
		Response response = new Response();
		response.setError(error);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
//		logger.debug("response class is " + Data.class);
//		logger.debug("response status is " + httpStatus.toString());
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpStatus);
		return responseEntity;
	}

	public static ResponseEntity<Response> successGetResponse(String message, Object object, HttpStatus httpStatus) {

		Response response = new Response();
		response.setData(object);
		response.setMessage(message);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
//		logger.debug("response class is " + Data.class);
//		logger.debug("response status is " + httpStatus.toString());
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpStatus);
		return responseEntity;
	}
}
