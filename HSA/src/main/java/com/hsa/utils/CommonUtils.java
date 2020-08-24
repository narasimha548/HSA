package com.hsa.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

	final static String ALPHA_NUMERIC_STRING="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static final List<String> getRoles(){
		List<String> listType= Arrays.asList("ADMIN","CASE WORKER");
		return listType;
	}
	
	public static final List<String> getGenders(){
		List<String> gendersList= Arrays.asList("MALE","FE-MALE","OTHERS");
		return gendersList;
	}
	

	public static String getRandomPassword() {
		
		int count=6;
		StringBuilder sb = new StringBuilder();
		while (count-- != 0) {
			
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			  sb.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return sb.toString();
	}
	
}
