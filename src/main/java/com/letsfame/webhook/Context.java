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
	public String id;
	public Date ts;
	public Object pipeline_id;
	public String workflow_id;
	public String deployment_id;
	public String source_type;
	public boolean verified;
	public Object hops;
	public boolean test;
	public boolean replay;
	public String owner_id;
	public String platform_version;
	public String workflow_name;
	public Object resume;
	public String trace_id;

}
