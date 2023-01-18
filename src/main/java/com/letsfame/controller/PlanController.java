package com.letsfame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.PlanReq;
import com.letsfame.repository.PlanRequestReository;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.PlanService;

//import com.razorpay.Plan;
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;

import io.swagger.annotations.ApiOperation;
import lombok.NonNull;

@RestController()
public class PlanController {

//	@Autowired
//	private RazorpayClient razorpayClient;

	private @NonNull ResponseHandler responseGenerator;
	
	@Autowired
	private PlanService planservice;
	
	@Autowired
	private PlanRequestReository planRequestReository;
	
	

	@PostMapping("/txn_api/v1.0/create/plans")
	@ApiOperation(value = "Create/Update plans Details")

	public ResponseEntity<?> createPlan(@RequestBody PlanReq req) {

		PlanReq res = new PlanReq();
		try {

			if (req != null) {
				res = planRequestReository.save(req);
			}

			//return new ResponseEntity<>(res, HttpStatus.OK);
			return ResponseHandler.generateResponse(HttpStatus.OK, res,"Plan Create successfully..");
	} catch (Exception e) {

		e.printStackTrace();
		System.out.println("Error in createOrUpdate" + e.getMessage());
		return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);

	}

	}

	@GetMapping("/txn_api/v1.0/getAll/plans")
	@ApiOperation(value = "Get/plans Details")

	public ResponseEntity<?> getAllPlan() {

//		PlanReq res = new PlanReq();
		try {

			List<PlanReq> plans = planRequestReository.findAll();

			return ResponseHandler.generateResponse(HttpStatus.OK, plans,"Plan Fetched successfully..");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Error in createOrUpdate" + e.getMessage());
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

//	public ResponseEntity<Response> getPlans()
//	{
//		Response data = new Response();
//		PlanReq allPlans = planservice.getPlans();
//
//			return new allPlans;
//		}
//
//	}
	
//	@PostMapping(path = "/create/plans", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> plan(@RequestBody final PlanRequest planRequest) {
//
//		JSONObject message = new JSONObject();
//		try {
//
//			Plan plan = razorpayClient.plans.create(planRequest.toJsonObject());
//			message = plan.toJson();
//			System.out.println(message);
//			if(plan!=null) {
//				planRequestReository.save(planRequest);
//			}
//
//		} catch (RazorpayException e) {
//
//			// Handle Exception
//			message.put("error", e.getMessage());
//			System.out.println(e.getMessage());
//
//		}
//		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
//	}

//	@GetMapping(path = "/FetchAllPlans", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> Allplans() {
//
//		JSONObject message = new JSONObject();
//		try {
//
//			List<Plan> plans = razorpayClient.plans.fetchAll();
//
//			return new ResponseEntity<>(plans.toString(), HttpStatus.OK);
//
//		} catch (RazorpayException e) {
//
//			// Handle Exception
//			message.put("error", e.getMessage());
//			System.out.println(e.getMessage());
//
//		}
//		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
//	}
//
//	@PostMapping(path = "/FetchOnePlan", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> Oneplan(@RequestBody final PlanRequest planRequest) {
//
//		JSONObject message = new JSONObject();
//		try {
//
//			Plan plan = razorpayClient.plans.fetch(planRequest.getId());
//
//			return new ResponseEntity<>(plan.toString(), HttpStatus.OK);
//
//		} catch (RazorpayException e) {
//
//			// Handle Exception
//			message.put("error", e.getMessage());
//			System.out.println(e.getMessage());
//
//		}
//		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
//	}
}
