package com.letsfame.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PlanService implements PlanServiceImpl {
	
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
	

