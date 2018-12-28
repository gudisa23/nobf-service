package com.att.api.nobf.repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.att.api.nobf.model.OrderNotify;


public interface OrderNotifyRepository extends MongoRepository<OrderNotify, String> {
	
		public ArrayList<OrderNotify> findByCreateDateAfter(Date date);

}
