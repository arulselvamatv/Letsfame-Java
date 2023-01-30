package com.letsfame.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsfame.bean.PlanFeaturesReq;
import com.letsfame.bean.PlanReq;
import com.letsfame.repository.PlanFeaturesRepository;
import com.letsfame.repository.PlanRequestReository;
import com.letsfame.response.ResponseDto;
import com.letsfame.service.PlanService;
import com.razorpay.Plan;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRequestReository planRequestReository;

	@Autowired
	private PlanFeaturesRepository planFeaturesRepository;

	@Autowired
	private RazorpayClient razorpayClient;

	@Override
	public ResponseDto createPlan(PlanReq req) {
		ResponseDto response = new ResponseDto();
		List<String> error = new ArrayList<>();
		PlanReq savedData = new PlanReq();
		try {

			if (req != null) {
				savedData = planRequestReository.save(req);
			} else {
				error.add("Plan request should not be null");
			}

			// valdiation
			// error.add("error");

			if (error.isEmpty()) {
				response.setData(savedData);
				response.setStatus("Success");
			} else {

				response.setStatus("Failed");
				response.setMessages(error);
			}
			// return res;

		} catch (Exception e) {

			response.setStatus("Failed");

			error.add(e.getMessage());
			response.setMessages(error);
			// response.getMessages().add(e.getMessage());

			System.out.println("Error :: createPlan :: Exception::" + ExceptionUtils.getStackTrace(e));

		}
		return response;
	}

	@Override
	public ResponseDto getPlans() {
		ResponseDto response = new ResponseDto();
		List<String> error = new ArrayList<>();
		List<PlanReq> savedData = new ArrayList<PlanReq>();
//		List<Plan> Plans = new ArrayList<Plan>();

		try {

//			Plans = razorpayClient.plans.fetchAll();
//			response.setData(Plans);
//			response.setStatus("Success");

			savedData = planRequestReository.findAll();

			response.setData(savedData);
			response.setStatus("Success");

		} catch (Exception e) {
			response.setStatus("Failed");

			error.add(e.getMessage());
			response.setMessages(error);
			response.getMessages().add(e.getMessage());

			System.out.println("Error :: createPlan :: Exception::" + ExceptionUtils.getStackTrace(e));

		}

//		try {
//
//			savedData = planRequestReository.findAll();
//
//			response.setData(savedData);
//			response.setStatus("Success");
//		}
//
//		catch (Exception e) {
//
//			response.setStatus("Failed");
//
//			error.add(e.getMessage());
//			response.setMessages(error);
//			// response.getMessages().add(e.getMessage());
//
//			System.out.println("Error :: createPlan :: Exception::" + ExceptionUtils.getStackTrace(e));
//
//		}

		return response;
	}

	@Override
	public ResponseDto createPlanFeatures(PlanFeaturesReq req) {

		ResponseDto response = new ResponseDto();
		List<String> error = new ArrayList<>();
		PlanFeaturesReq savedData = new PlanFeaturesReq();

//				PlanReq res = new PlanReq();

		try {

			savedData = planFeaturesRepository.save(req);

			response.setData(savedData);
			response.setStatus("Success");
		}

		catch (Exception e) {

			response.setStatus("Failed");

			error.add(e.getMessage());
			response.setMessages(error);
			// response.getMessages().add(e.getMessage());

			System.out.println("Error :: createPlan :: Exception::" + ExceptionUtils.getStackTrace(e));

		}

		return response;
	}

	@Override
	public ResponseDto getPlanFeatures() {
		ResponseDto response = new ResponseDto();
		List<String> error = new ArrayList<>();
		List<PlanFeaturesReq> savedData = new ArrayList<PlanFeaturesReq>();

//			PlanReq res = new PlanReq();

		try {

			savedData = planFeaturesRepository.findAll();

			response.setData(savedData);
			response.setStatus("Success");
		}

		catch (Exception e) {

			response.setStatus("Failed");

			error.add(e.getMessage());
			response.setMessages(error);
			// response.getMessages().add(e.getMessage());

			System.out.println("Error :: createPlan :: Exception::" + ExceptionUtils.getStackTrace(e));

		}

		return response;
	}

//	@Override
//	public ResponseDto getPlan(PlanReq req) {
//		
//		ResponseDto response = new ResponseDto();
//		List<String> error = new ArrayList<>();
//		List<PlanReq> savedData = new ArrayList<PlanReq>();
//		// TODO Auto-generated method stub
//		
//		
//
//		try {
//
//			 savedData = planRequestReository.findById(null)
//
//			response.setData(savedData);
//			response.setStatus("Success");
//		}
//		
//		
//		return null;
//	}

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
