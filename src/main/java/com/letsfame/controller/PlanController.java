package com.letsfame.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.Req.LetsFamePaymentReq;
import com.letsfame.Req.LetsFamePlanReq;
import com.letsfame.bean.LetsFamePlan;
import com.letsfame.response.Response;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.PlanService;
import com.razorpay.Payment;
import com.razorpay.Plan;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor(onConstructor_ = { @Autowired })
@RequestMapping("/txn_api/v1.0/plan")
public class PlanController {

	@Autowired
	private PlanService planservice;

	@ApiOperation(value = "Create or Update plans Details", response = Response.class)
	@PostMapping(produces = "application/json")
	public ResponseEntity<?> createPlan(@RequestBody LetsFamePlanReq req) throws Exception {

		try {
			Plan res = planservice.createPlan(req);

			return ResponseHandler.successGetResponse("Created successfully.", res.toJson().toMap(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error :: createPlan :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Allows to fetch Plan Details", response = Response.class)
	@GetMapping(produces = "application/json")
	public ResponseEntity<?> getAllPlans() throws Exception {
		try {
			List<LetsFamePlan> res = planservice.getPlans();
			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error :: getAllPlans :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Allows to fetch Plan Details", response = Response.class)
	@GetMapping(value = "/{planId}", produces = "application/json")
	public ResponseEntity<?> getPlanById(@PathVariable String planId) throws Exception {

		try {
			LetsFamePlan res = planservice.getPlan(planId);

			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("Error :: getPlanById :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Update plan details", response = Response.class)
	@PostMapping(value = "/Update", produces = "application/json")
	public ResponseEntity<?> updatePlanDetails(@RequestBody LetsFamePlanReq req) throws Exception {

		try {
			Plan res = planservice.updatePlanDetails(req);

			return ResponseHandler.successGetResponse("Created successfully.", res.toJson().toMap(), HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("Error :: updatePlanDetails :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
