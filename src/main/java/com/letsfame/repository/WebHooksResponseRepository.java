package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.letsfame.bean.WebHooksResponse;

public interface WebHooksResponseRepository extends MongoRepository<WebHooksResponse, Integer>{

}
