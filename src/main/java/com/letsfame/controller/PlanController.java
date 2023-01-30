package com.letsfame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.PlanFeaturesReq;
import com.letsfame.bean.PlanReq;
import com.letsfame.response.ResponseDto;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.PlanService;

//import com.razorpay.Plan;
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;

import io.swagger.annotations.ApiOperation;

@RestController()
public class PlanController {

//	@Autowired
//	private RazorpayClient razorpayClient;

//	private @NonNull ResponseHandler responseGenerator;

	@Autowired
	private PlanService planservice;

	@PostMapping("/txn_api/v1.0/create/plans")
	@ApiOperation(value = "Create/Update plans Details")
	public ResponseEntity<?> createPlan(@RequestBody PlanReq req) {
		try {
			ResponseDto res = planservice.createPlan(req);
			// return ResponseHandler.generateResponse(HttpStatus.OK,
			// planservice.createPlan(req), null);
			return ResponseHandler.generateResponse(HttpStatus.OK, res);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/txn_api/v1.0/plans")
	@ApiOperation(value = "Get/plans Details")

	public ResponseEntity<?> getAllPlan() {

//		PlanReq res = new PlanReq();
		try {

			ResponseDto res = planservice.getPlans();

			return ResponseHandler.generateResponse(HttpStatus.OK, res);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/txn_api/v1.0/create/plansFeatures")
	@ApiOperation(value = "Create/update/plansFeatures Details")

	public ResponseEntity<?> createPlanFeatures(PlanFeaturesReq req) {

//			PlanReq res = new PlanReq();
		try {

			ResponseDto res = planservice.createPlanFeatures(req);

			return ResponseHandler.generateResponse(HttpStatus.OK, res);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/txn_api/v1.0/plan/features")
	@ApiOperation(value = "Get/features list")

	public ResponseEntity<?> getAllFeatures() {

		try {

			ResponseDto res = planservice.getPlanFeatures();

			return ResponseHandler.generateResponse(HttpStatus.OK, res);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//		@PostMapping(path = "/txn_api/v1.0/one_plan")
//		public ResponseEntity<?> onePlan(@RequestBody  PlanReq req) {
//	
//		
//			try {
//				ResponseDto res = planservice.getPlan();
//	
////				Plan plan = razorpayClient.plans.fetch(planRequest.getId());
//	
//				return ResponseHandler.generateResponse(HttpStatus.OK, res);
//	
//			} catch (Exception e) {
//
//				return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			
//		}
}

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
