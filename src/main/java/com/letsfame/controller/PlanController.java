package com.letsfame.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.Plan;
import com.letsfame.request.PlanCreateRequest;
import com.letsfame.response.Response;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.PlanService;

import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping("/txn_api")
public class PlanController {

	@Autowired
	private PlanService planservice;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "Create or Update plans Details", response = Response.class)
	@PostMapping(value = "/v1.0/plan", produces = "application/json")
	public ResponseEntity<?> createPlan(@RequestBody PlanCreateRequest req) throws Exception {

		try {
			Plan res = planservice.createPlan(req);

			return ResponseHandler.successGetResponse("Created successfully.", res, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error :: createPlan :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Allows to fetch Plan Details", response = Response.class)
	@GetMapping(value = "/v1.0/plan", produces = "application/json")
	public ResponseEntity<?> findAllPlans() throws Exception {
		try {
			List<Plan> res = planservice.getPlans();
			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error :: findAllPlans :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Allows to fetch Plan Details", response = Response.class)
	@GetMapping(value = "/v1.0/{planId}", produces = "application/json")
	public ResponseEntity<?> findByPlanId(@PathVariable String planId) throws Exception {

		try {
			Plan res = planservice.getPlan(planId);

			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error :: findByPlanId :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Update plan details", response = Response.class)
	@PutMapping(value = "/v1.0/plan", produces = "application/json")
	public ResponseEntity<?> updatePlanDetails(@RequestBody PlanCreateRequest req) throws Exception {

		try {
			Plan res = planservice.updatePlanDetails(req);

			return ResponseHandler.successGetResponse("Created successfully.", res, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error :: updatePlanDetails :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
