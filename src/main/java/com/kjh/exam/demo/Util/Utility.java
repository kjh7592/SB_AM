package com.kjh.exam.demo.Util;

public class Utility {

	public static boolean empty(Object obj) {
		
		if(obj == null) {
			return true;
		}
		
//		if(obj instanceof String == false) {
//			return true;
//		}
		
		String str = (String) obj;
		
		return str.trim().length() == 0;
	}

}