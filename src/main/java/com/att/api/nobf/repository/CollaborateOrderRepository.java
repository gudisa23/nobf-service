package com.att.api.nobf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.att.api.nobf.model.CollaborateOrder;


public interface CollaborateOrderRepository extends MongoRepository<CollaborateOrder, String> {

}
