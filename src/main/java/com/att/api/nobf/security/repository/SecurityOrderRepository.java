package com.att.api.nobf.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.att.api.nobf.security.model.SecurityOrder;

public interface SecurityOrderRepository extends MongoRepository<SecurityOrder, String> {

}
