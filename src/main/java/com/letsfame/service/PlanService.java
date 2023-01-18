package com.letsfame.service;

import java.util.List;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.letsfame.bean.PlanReq;
import com.letsfame.bean.PlanRequest;
import com.letsfame.repository.PlanRequestReository;
import com.letsfame.response.Response;


@Service
@Transactional
public class PlanService {
	
//	@Autowired
//	public PlanRequestReository planRequestReository;
//
//	
//		
//		public ResponseEntity<Response> getPlans() {
//			
//			ResponseEntity<Response> res = new ResponseEntity<Response>();
//		
////			PlanReq res = new PlanReq();
//			try {
//
//				List<PlanReq> plans = planRequestReository.findAll();
//
//				return ResponseEntity<Response>;
//			}
//
//			catch (Exception e) {
//
//				System.out.println(e.getMessage());
//				return new ResponseEntity<>("status:failed,message:" + e.getMessage(), HttpStatus.BAD_REQUEST);
//			}
//
//		}

	}
//	
//	@Autowired
//	private RazorpayClient razorpayClient;
//
//	@Value("${razorpay.key.id}")
//	private String keyId;
//	@Value("${razorpay.key.secret}")
//	private String keySecret;
//	
//	private RazorpayClient client;
//	
//	
//	public JSONObject save(@Valid PlanRequest request) throws RazorpayException{
//		JSONObject main = new JSONObject();
//		JSONObject message = new JSONObject();
//		RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);
//		try {
//
//			JSONObject jsonObject = request.toJsonObject();
//			Plan plan = razorpay.plans.create(jsonObject);
//			
//			message = plan.toJson();
//			return message;
//		} catch (RazorpayException e) {
//			main.put("error", e.getMessage());
//			System.out.println(e.getMessage());
//			return main;
//		}
//		
//		
//
//		
//	}
//
//	public List<Plan> fetchAll() throws RazorpayException {
//		//RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);
//		this.client =  new RazorpayClient(keyId, keySecret);
//		return client.plans.fetchAll();
//	}
	

