package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.Orders;

@Repository
public interface OrderRepository extends MongoRepository<Orders, String> {

}
