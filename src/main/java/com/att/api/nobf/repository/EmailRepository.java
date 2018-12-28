package com.att.api.nobf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.att.api.nobf.model.Email;

public interface EmailRepository extends MongoRepository<Email, String> {

}
