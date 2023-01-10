package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.PlanRequest;

@Repository
public interface PlanRequestReository extends MongoRepository<PlanRequest, String>{

}
