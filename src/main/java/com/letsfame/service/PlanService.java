package com.letsfame.service;

import java.util.List;
import java.util.Optional;

import com.letsfame.bean.Plans;
import com.razorpay.Plan;
import com.razorpay.RazorpayException;

public interface PlanService {

	Plan createPlan(Plans req) throws RazorpayException;

	List<Plans> getPlans();

	Optional<Plans> getPlan(String id);

//	ResponseDto createPlanFeatures(PlanFeaturesReq req);
//
//	ResponseDto getPlanFeatures();

}