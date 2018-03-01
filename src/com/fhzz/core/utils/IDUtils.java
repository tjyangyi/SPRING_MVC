/**
 * 
 */
package com.fhzz.core.utils;

import java.util.UUID;

/**
 * @author YangYi
 * 
 */
public class IDUtils {
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
	
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
}
