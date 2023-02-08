package com.letsfame.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.letsfame.bean.Item;
import com.letsfame.bean.Notes;
import com.letsfame.bean.Plans;
import com.letsfame.repository.PlanRequestReository;
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
	private RazorpayClient razorpayClient;

	@Override
	public Plan createPlan(Plans req) throws RazorpayException {

		Plans savedData = new Plans();
		Plans savedData1 = new Plans();
		Item saveItem = new Item();
		Notes saveNotes = new Notes();
		
		savedData.setInterval(req.getInterval());
		savedData.setPeriod(req.getPeriod());
		savedData.setItem(req.getItem());
		savedData.setNotes(req.getNotes());
		
		Plan plan = razorpayClient.plans.create(new JSONObject(new Gson().toJson(savedData)));

		if (plan != null) {
			savedData1.setPlanId(plan.toJson().getString("id"));
			savedData1.setInterval(plan.toJson().getInt("interval"));
			savedData1.setPeriod(plan.toJson().getString("period"));
			
			JSONObject newJSONString = plan.toJson().getJSONObject("item");
			for (int i = 0; i < newJSONString.length(); i++) {
				saveItem.setId(newJSONString.getString("id"));
				saveItem.setName(newJSONString.getString("name"));
				saveItem.setAmount(newJSONString.getInt("amount"));
				saveItem.setCurrency(newJSONString.getString("currency"));
				savedData1.setItem(saveItem);
			}
			JSONObject newJSONNotes = plan.toJson().getJSONObject("notes");
			for (int i = 0; i < newJSONString.length(); i++) {
				saveNotes.setNotes_key_1(newJSONNotes.getString("notes_key_1"));
				saveNotes.setNotes_key_2(newJSONNotes.getString("notes_key_2"));
				savedData1.setNotes(saveNotes);
			}

			planRequestReository.save(savedData1);
		}
		return plan;
	}
	
	@Override
	public List<Plans> getPlans() {
		return planRequestReository.findAll();
	}

	@Override
	public Optional<Plans> getPlan(String id) {

		return planRequestReository.findById(id);
	
	}
}



//
//	@Override
//	public ResponseDto createPlanFeatures(PlanFeaturesReq req) {
//
//		ResponseDto response = new ResponseDto();
//		List<String> error = new ArrayList<>();
//		PlanFeaturesReq savedData = new PlanFeaturesReq();
//		try {
//			savedData = planFeaturesRepository.save(req);
//			response.setData(savedData);
//			response.setStatus("Success");
//		}
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
//
//		return response;
//	}
//
//	@Override
//	public ResponseDto getPlanFeatures() {
//		ResponseDto response = new ResponseDto();
//		List<String> error = new ArrayList<>();
//		List<PlanFeaturesReq> savedData = new ArrayList<PlanFeaturesReq>();
//
//		try {
//
//			savedData = planFeaturesRepository.findAll();
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
//
//		return response;
//	}


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
