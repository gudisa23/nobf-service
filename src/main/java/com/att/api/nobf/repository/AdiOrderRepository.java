package com.att.api.nobf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.att.api.nobf.model.ADIOrder;

public interface AdiOrderRepository extends MongoRepository<ADIOrder, String> {

}
