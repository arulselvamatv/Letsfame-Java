package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.LetsFameOrder;

@Repository
public interface OrderRepository extends MongoRepository<LetsFameOrder, String> {

}
