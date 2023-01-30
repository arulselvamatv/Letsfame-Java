package com.letsfame.service;

import com.letsfame.bean.PlanFeaturesReq;
import com.letsfame.bean.PlanReq;
import com.letsfame.response.ResponseDto;

public interface PlanService {

	ResponseDto createPlan(PlanReq req);

	ResponseDto getPlans();

	ResponseDto createPlanFeatures(PlanFeaturesReq req);

	ResponseDto getPlanFeatures();

//	ResponseDto getPlan(PlanReq req); 

}