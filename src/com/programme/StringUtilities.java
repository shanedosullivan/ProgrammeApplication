package com.programme;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class StringUtilities {

	public static String convertToRestUrl(String url, Map<String, String> params){
		String result = url;
		if(!params.isEmpty()){
			result+="?";
		}
		for(Map.Entry<String, String> param: params.entrySet()){
			result+=param.getKey()+"="+param.getValue()+"&";
		}
		result = result.substring(0, result.length()-1);
		return result;
	}
	
	public static String convertDateToString(Date date, String datePattern){
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		String dateString = null;
		dateString = sdf.format(date);
		return dateString;
	}
	
	public static String ellipsize(String s){
		String returnedString = s;
		if(s.length()>10){
			returnedString = s.substring(0, 7)+"...";
			}
		return returnedString;   
	}
}
