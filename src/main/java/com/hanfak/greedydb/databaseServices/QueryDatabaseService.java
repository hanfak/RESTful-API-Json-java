package com.hanfak.greedydb.databaseServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class QueryDatabaseService {
	public String getFieldValue(String streamName,
			String timestamp, 
			String jsonPath) {
		String value = "";
		DBCursor cursor = findJsonPathQuery(streamName, timestamp);
		value = retrieveValueOfJsonPath(cursor, jsonPath);
		cursor.close();
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
	
	private DBCollection DatabaseCollection() {
	    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		
		DB db = mongoClient.getDB( "test" );
		System.out.println("Connect to database successfully");
		
		DBCollection collection = db.getCollection("streams");
		System.out.println("Collection mycol selected successfully");
	   
	    return collection;
	 }
    
	private DBCursor findJsonPathQuery(String streamName, String timestamp) {
		BasicDBObject whereQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("streamName", streamName));
		obj.add(new BasicDBObject("timestamp", Integer.parseInt(timestamp)));
		whereQuery.put("$and", obj);
		return DatabaseCollection().find(whereQuery);
	}
    
}
