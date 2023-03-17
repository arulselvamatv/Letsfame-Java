package com.letsfame.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Error {

	private Object code;
	private String cause;
	private String message;
	private List<String> errorMessages;

	
	
}