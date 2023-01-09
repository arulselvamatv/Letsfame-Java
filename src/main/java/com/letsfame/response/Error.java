package com.letsfame.response;

import java.util.List;
import lombok.Data;

@Data
public class Error {

	
	private String Reason;
	private List<String> errorList;

	@Override
	public String toString() {
		return "Error [Reason=" + Reason + "]";
	}

	
}