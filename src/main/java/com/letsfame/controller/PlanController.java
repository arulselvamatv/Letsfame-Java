package com.letsfame.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.letsfame.bean.Plans;
import com.letsfame.response.Response;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.PlanService;
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
	@PostMapping(value = "/create", produces = "application/json")
	public ResponseEntity<?> createPlan(@RequestBody Plans req) throws Exception {
		try {
			Plan res = planservice.createPlan(req);
			return ResponseHandler.successGetResponse("Created successfully.", res.toJson().toMap(),HttpStatus.OK);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Allows to fetch Plan Details", response = Response.class)
	@GetMapping(value = "/getAll", produces = "application/json")
	public ResponseEntity<?> getAllPlan() throws Exception {
		try {
			List<Plans> res = planservice.getPlans();
			return ResponseHandler.successGetResponse("Fetched successfully.", res,HttpStatus.OK);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@ApiOperation(value = "Create or Update plansFeatures Details", response = Response.class)
//	@PostMapping(value = "/plansFeatures/create", produces = "application/json")
//
//	public ResponseEntity<?> createPlanFeatures(PlanFeaturesReq req) throws Exception {
//		try {
//			ResponseDto res = planservice.createPlanFeatures(req);
//
//			return ResponseHandler.generateResponse(HttpStatus.OK, res);
//		} catch (Exception e) {
//
//			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@ApiOperation(value = "Allows to fetch plansFeatures Details", response = Response.class)
//	@GetMapping(value = "/plansFeatures/getAll", produces = "application/json")
//	public ResponseEntity<?> getAllFeatures(PlanFeaturesReq req) throws Exception {
//
//		try {
//
//			ResponseDto res = planservice.getPlanFeatures();
//
//			return ResponseHandler.generateResponse(HttpStatus.OK, res);
//		} catch (Exception e) {
//
//			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@ApiOperation(value = "Allows to fetch Plan Details", response = Response.class)
//	@GetMapping(value = "/getById", produces = "application/json")
//	public ResponseEntity<?> onePlan(@RequestParam String id) throws Exception {
//
//		try {
//			Optional<Plans> res = planservice.getPlan(id);
//
//			return ResponseHandler.generateResponse(HttpStatus.OK, res);
//
//		} catch (Exception e) {
//
//			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}
}
