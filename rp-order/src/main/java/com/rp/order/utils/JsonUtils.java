package com.rp.order.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonUtils {
	
	public static String prettyPrint(String text) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(text);
		String prettyJson = gson.toJson(je);
		return prettyJson;
	}

}
