package com.att.api.nobf.dtv.integration;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.att.api.nobf.dtv.model.BusinessCustomer;

public interface DTVRFIRepository extends MongoRepository<BusinessCustomer, String> {

}
