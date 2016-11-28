package com.hanfak.greedydb.databaseConnections;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBConnectionManager {
	public DBCollection retrieveCollection() {
	    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		
		DB db = mongoClient.getDB( "test" );
		System.out.println("Connect to database successfully");
		
		DBCollection collection = db.getCollection("streams");
		System.out.println("Collection mycol selected successfully");
	   
	    return collection;
	 }
}
