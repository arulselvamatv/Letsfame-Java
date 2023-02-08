package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.Req.PlanFeaturesReq;

@Repository
public interface PlanFeaturesRepository extends MongoRepository<PlanFeaturesReq, String> {

}
