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
		// To connect to mongodb server
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		
		// Now connect to your databases
		DB db = mongoClient.getDB( "test" );
		System.out.println("Connect to database successfully");
		
		DBCollection coll = db.getCollection("streams");
		System.out.println("Collection mycol selected successfully");

		BasicDBObject whereQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("streamName", streamName));
		obj.add(new BasicDBObject("timestamp", Integer.parseInt(timestamp)));
		whereQuery.put("$and", obj);
		//if statement for streamName
		//whereQuery.put("streamName", "clicks");
		System.out.println(whereQuery.toString());
		DBCursor cursor = coll.find(whereQuery);
		
		
		String value = "";

		while (cursor.hasNext()) { 
			DBObject obj1 = cursor.next();
			//field path, split into arrayList, then iterate, and perform
			//another get(path) if arrayList not empty
			System.out.println(jsonPath);
			//Get list of strings for jsonpath
			List<String> items = Arrays.asList(jsonPath.split("\\s*/\\s*"));
			System.out.println(Arrays.toString(items.toArray()));
			//for loop 
			if(items.size() == 2){
				value = (String) ((DBObject) obj1.get(items.get(0))).get(items.get(1));
			}
			if(items.size() == 1) {
				value = (String)  obj1.get(jsonPath);
			}
			System.out.println(value);
		}
		
		cursor.close();
		return value;
		}
	
    
}
