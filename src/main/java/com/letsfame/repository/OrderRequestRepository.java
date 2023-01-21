package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.OrderRequest;

@Repository
public interface OrderRequestRepository extends MongoRepository<OrderRequest, String> {

}
