package com.letsfame.service;

import java.util.List;

import com.letsfame.Req.LetsFamePlanReq;
import com.letsfame.bean.LetsFamePlan;
import com.razorpay.Plan;;

public interface PlanService {

	Plan createPlan(LetsFamePlanReq req) throws Exception;
	
	Plan updatePlanDetails(LetsFamePlanReq req)throws Exception;

	List<LetsFamePlan> getPlans() throws Exception;

	LetsFamePlan getPlan(String planId) throws Exception;

}