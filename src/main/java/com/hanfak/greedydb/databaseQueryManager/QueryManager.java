package com.hanfak.greedydb.databaseQueryManager;

import java.util.ArrayList;
import java.util.List;

import com.hanfak.greedydb.databaseConnections.MongoDBConnectionManager;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class QueryManager {
	private MongoDBConnectionManager connectToDB;
	
	public QueryManager(MongoDBConnectionManager connectToDB){
		this.connectToDB = connectToDB;
	}
	
	public DBCursor findJsonPathQuery(String streamName, String timestamp) {
		BasicDBObject whereQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("streamName", streamName));
		obj.add(new BasicDBObject("timestamp", Integer.parseInt(timestamp)));
		whereQuery.put("$and", obj);
		return connectToDB.retrieveCollection().find(whereQuery);
	}
	
    public DBCursor allStreamNameQuery(String streamName) {
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("streamName", streamName);
	    return connectToDB.retrieveCollection().find(whereQuery);
    }
    
    public DBCursor findTimedJsonPathQuery(String streamName, int orderValue) {
    	BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("streamName", streamName);
	    DBCursor cursor = connectToDB.retrieveCollection().find(whereQuery);
	    return cursor.sort(new BasicDBObject("timestamp", orderValue)).limit(1);
    }
}
