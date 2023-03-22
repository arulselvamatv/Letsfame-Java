package com.letsfame.service;

import java.util.List;

import com.letsfame.bean.Plan;
import com.letsfame.request.PlanCreateRequest;

public interface PlanService {

	Plan createPlan(PlanCreateRequest req) throws Exception;

	Plan updatePlanDetails(PlanCreateRequest req) throws Exception;

	List<Plan> getPlans() throws Exception;

	Plan getPlan(String planId) throws Exception;

}