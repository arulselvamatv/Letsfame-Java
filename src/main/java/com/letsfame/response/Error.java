package com.letsfame.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {

	private String code;
	private String Reason;
	private List<String> errorList;

	@Override
	public String toString() {
		return "Error [Reason=" + Reason + "]";
	}

	
}