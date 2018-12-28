package com.att.api.nobf.dtv.integration;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.att.api.nobf.dtv.model.DirectvOrder;

public interface DTVOrderRepository extends MongoRepository<DirectvOrder, String> {

}
