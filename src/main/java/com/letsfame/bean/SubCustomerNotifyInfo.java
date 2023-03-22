package com.letsfame.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubCustomerNotifyInfo implements Serializable {

	private static final long serialVersionUID = -5517434780253648339L;
	private String notifyPhone;
	private String notifyEmail;

}