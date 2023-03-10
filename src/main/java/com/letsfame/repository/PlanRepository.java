package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.LetsFamePlan;

@Repository
public interface PlanRepository extends MongoRepository<LetsFamePlan, String> {
	LetsFamePlan findByPlanId(String planId);

}
