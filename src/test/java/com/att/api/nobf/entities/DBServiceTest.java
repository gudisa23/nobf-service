package com.att.api.nobf.entities;

import java.util.Arrays;

import org.bson.Document;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.att.api.nobf.db.DBService;
import com.att.api.nobf.dto.RFI;
import com.att.api.nobf.dto.RFICustomer;
import com.att.api.nobf.model.CollaborateOrder;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SpringBootApplication
public class DBServiceTest{}
//implements CommandLineRunner{
//	
//	
//	
//	@Autowired
//	private OrderContactRepository repository;
//
//	public static void main(String[] args) {
//		SpringApplication.run(DBServiceTest.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		
//	
//	 	System.out.println("before save call");
//	 	CollaborateOrder order = new CollaborateOrder();
//	 	order.setNumberOfUsers(5);
//	 	repository.save(order);
//		for (CollaborateOrder customer : repository.findAll()) {
//			System.out.println(customer);
//		}
//		System.out.println("after save call");
//
//	}
//	
////	
////	@Test
////	public void assertSpringDBTest() {
////		CollaborateOrder order = new CollaborateOrder();
////		order.setNumberOfUsers(5);
////		dbService.saveCustomerOrder(order);
////	}
////	
////	//@Test
////	public void assertDBSaveTest() {
////		try {
////
////			RFI rfi = new RFI();
////			Prospect prospect = new Prospect();
////			prospect.setFirstName("Scott");
////			prospect.setLastName("Tiger");
////			prospect.setCompanyName("BCG");
////			prospect.setEmailAddress("xyz@gmail.com");
////			prospect.setPhoneNumber("9999999999");
////			rfi.setProspect(prospect);
////
////			// rfi.setProductInterest("ADI");
////
////			Document documentCollaborate = new Document("title", "Collaborate")
////					.append("description", "Collaborate Order details")
////					.append(RFICustomer.customerName, prospect.getFirstName() + " " + prospect.getLastName())
////					.append(RFICustomer.customerEmail, prospect.getEmailAddress())
////					.append(RFICustomer.customerPhoneNumber, prospect.getPhoneNumber());
////			Document documentorderNotify = new Document("title", "OrderNotify").append(RFICustomer.emailSent, "Yes")
////					.append(RFICustomer.orderData, new Document("collaborate", documentCollaborate))
////					.append(RFICustomer.dateSubmited, "Yes").append(RFICustomer.resentDate, "")
////					.append(RFICustomer.emailSent, "Yes").append(RFICustomer.orderType, "Collaborate");
////
////			// Creating Credentials
////			MongoCredential credential;
////			credential = MongoCredential.createCredential("nav-admin", "admin", "Quickstrike".toCharArray());
////			MongoClient mongo = new MongoClient(new ServerAddress("18.188.100.110", 8080), Arrays.asList(credential)); // dev
////																														// database
////
////			MongoDatabase database = mongo.getDatabase("admin");
////			System.out.println("Credentials ::" + credential);
////
////			MongoCollection<Document> collection = database.getCollection("OrderCollection");
////
////			// Document documentorderNotify = new Document("title", "OrderNotify")
////			// .append(RFICustomer.emailSent, "Yes")
////			// .append(RFICustomer.orderData, new Document("anotherdoc","documentadi"))
////			// .append(RFICustomer.dateSubmited, "Yes")
////			// .append(RFICustomer.resentDate, "")
////			// .append(RFICustomer.emailSent, "Yes")
////			// .append(RFICustomer.orderType, "collaborate");
////			//
////
////			collection.insertOne(documentorderNotify);
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		System.out.println("hi");
////	}
//
//}
