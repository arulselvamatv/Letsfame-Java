package com.letsfame.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WebhookContext {
	private String id;
	private Date ts;
	private Object pipelineId;
	private String workflowId;
	private String deploymentId;
	private String sourceType;
	private boolean verified;
	private Object hops;
	private boolean test;
	private boolean replay;
	private String ownerId;
	private String platformVersion;
	private String workflowName;
	private Object resume;
	private String traceId;

}
