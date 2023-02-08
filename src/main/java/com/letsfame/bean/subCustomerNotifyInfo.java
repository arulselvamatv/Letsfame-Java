package com.letsfame.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class subCustomerNotifyInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	public String notify_phone;
	public String notify_email;

}