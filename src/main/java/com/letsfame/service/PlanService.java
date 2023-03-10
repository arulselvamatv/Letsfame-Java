package com.letsfame.service;

import java.util.List;

import com.letsfame.bean.LetsFamePlan;
import com.letsfame.request.PlanCreateRequest;
import com.razorpay.Plan;;

public interface PlanService {

	Plan createPlan(PlanCreateRequest req) throws Exception;
	
	Plan updatePlanDetails(PlanCreateRequest req)throws Exception;

	List<LetsFamePlan> getPlans() throws Exception;

	LetsFamePlan getPlan(String planId) throws Exception;

}