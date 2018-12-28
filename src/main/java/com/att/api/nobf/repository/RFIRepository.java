package com.att.api.nobf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.att.api.nobf.dto.RFI;

public interface RFIRepository extends MongoRepository<RFI, String> {


}
