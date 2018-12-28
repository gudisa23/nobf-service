package com.att.api.nobf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.att.api.nobf.model.FalloutCallbackInfo;

public interface FalloutCallbackInfoRepository extends MongoRepository<FalloutCallbackInfo, String> {

}
