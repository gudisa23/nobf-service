package com.att.api.nobf.security.service;

import java.util.List;
import java.util.Arrays;
public class MethodRefEx1 {
	public static void main(String[] args) {
		List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
		friends.forEach(friend -> System.out.println(friend));
	}
}
