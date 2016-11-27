package com.hanfak.greedydb.databaseServices;

import java.util.Arrays;
import java.util.List;

import com.hanfak.greedydb.databaseQueryManager.QueryManager;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class QueryDatabaseService {
	private QueryManager queryManager = new QueryManager();
	
	public String getFieldValue(String streamName,
								String timestamp, 
								String jsonPath) {
		String value = "";
		try{
			DBCursor cursor = queryManager.findJsonPathQuery(streamName, timestamp);
			value = retrieveValueOfJsonPath(cursor, jsonPath);
			cursor.close();
			return value;
		} catch(Exception e) {
			System.err.println(e);
		}
		return value;
	}
	
	private String retrieveValueOfJsonPath( DBCursor cursor, String jsonPath){
		String value = "";
		while (cursor.hasNext()) { 
			DBObject obj1 = cursor.next();
			List<String> items = Arrays.asList(jsonPath.split("\\s*/\\s*"));
			if(items.size() == 2){
				value = (String) ((DBObject) obj1.get(items.get(0))).get(items.get(1));
			}
			if(items.size() == 1) {
				value = (String)  obj1.get(jsonPath);
			}
		}
		return value;
	}
}
