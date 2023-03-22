package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.Plan;

@Repository
public interface PlanRepository extends MongoRepository<Plan, String> {
	Plan findByPlanId(String planId);

}
