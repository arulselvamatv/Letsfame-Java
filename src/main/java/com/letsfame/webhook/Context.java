package com.letsfame.webhook;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Context {
	private String id;
	private Date ts;
	private Object pipeline_id;
	private String workflow_id;
	private String deployment_id;
	private String source_type;
	private boolean verified;
	private Object hops;
	private boolean test;
	private boolean replay;
	private String owner_id;
	private String platform_version;
	private String workflow_name;
	private Object resume;
	private String trace_id;

}
