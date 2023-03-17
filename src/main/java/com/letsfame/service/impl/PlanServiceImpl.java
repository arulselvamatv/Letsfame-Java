package com.letsfame.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsfame.bean.Plan;
import com.letsfame.bean.PlanItem;
import com.letsfame.repository.PlanRepository;
import com.letsfame.request.PlanCreateRequest;
import com.letsfame.service.PlanService;

import com.razorpay.RazorpayClient;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepository planRequestReository;

	@Autowired
	private RazorpayClient razorpayClient;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Plan createPlan(PlanCreateRequest req) throws Exception {

		JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(req));

		Plan savedData = new Plan();

		com.razorpay.Plan plan = razorpayClient.plans.create(jsonObject);

		if (plan != null) {
			savedData = razorPayPlanToLetsFamePlan(plan);
			planRequestReository.save(savedData);
		}

		return savedData;
	}

	@Override
	public Plan updatePlanDetails(PlanCreateRequest req) throws Exception {

		Plan planUpdateData = new Plan();

		System.out.println("here  ::" + req);

		JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(req));

		com.razorpay.Plan plan = razorpayClient.plans.patch(req.getId(), jsonObject);

		if (plan != null) {

			planUpdateData = razorPayPlanToLetsFamePlan(plan);

			planRequestReository.save(planUpdateData);

		}
		return planUpdateData;
	}

	private Plan razorPayPlanToLetsFamePlan(com.razorpay.Plan plan) {
		Plan savedData1 = new Plan();
		PlanItem saveItem = new PlanItem();

		JSONObject planJsonObject = plan.toJson();
		savedData1.setPlanId(planJsonObject.getString("id"));
		savedData1.setInterval(planJsonObject.getInt("interval"));
		savedData1.setPeriod(planJsonObject.getString("period"));

		JSONObject planItemJsonObject = plan.toJson().getJSONObject("item");

		for (int i = 0; i < planItemJsonObject.length(); i++) {
			saveItem.setId(planItemJsonObject.getString("id"));
			saveItem.setName(planItemJsonObject.getString("name"));
			saveItem.setAmount(planItemJsonObject.getInt("amount"));
			saveItem.setCurrency(planItemJsonObject.getString("currency"));

		}
		savedData1.setItem(saveItem);

		return savedData1;

	}

	@Override
	public List<Plan> getPlans() throws Exception {
		return planRequestReository.findAll();
	}

	@Override
	public Plan getPlan(String planId) throws Exception {

		return planRequestReository.findByPlanId(planId);

	}
}
