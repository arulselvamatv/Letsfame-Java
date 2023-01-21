package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.PlanReq;

@Repository
public interface PlanRequestReository extends MongoRepository<PlanReq, String> {

}
