package com.att.api.nobf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.att.api.nobf.model.AdditionalInfo;

public interface AdditionalInfoRepository extends MongoRepository<AdditionalInfo, String> {

}
