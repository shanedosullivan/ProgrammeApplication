package com.programme.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseConverter {
	
	public String convertResponseToString(InputStream response) throws IOException{
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response));
		String line = "";
		String stringResult = "";
		
		while((line = bufferedReader.readLine())!=null){
			stringResult+=line;
		}
		return stringResult;
	}

}
