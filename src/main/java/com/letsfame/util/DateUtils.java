package com.letsfame.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

	public static Date getRazorPayTimeStamp(Object value) {
		if (value == null) {
			return null;
		}
		return new Date(TimeUnit.SECONDS.toMillis(Long.valueOf(value.toString())));

	}
}
