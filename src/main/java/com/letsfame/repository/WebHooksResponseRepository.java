package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.response.WebHooksResponse;

@Repository
public interface WebHooksResponseRepository extends MongoRepository<WebHooksResponse, Integer>{

}
