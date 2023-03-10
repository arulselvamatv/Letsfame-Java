package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.letsfame.bean.LetsFamePlanItem;


@Repository
public interface ItemRepository extends MongoRepository<LetsFamePlanItem, String>{

}
