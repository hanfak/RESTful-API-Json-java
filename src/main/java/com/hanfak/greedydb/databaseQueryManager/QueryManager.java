package com.hanfak.greedydb.databaseQueryManager;

import java.util.ArrayList;
import java.util.List;

import com.hanfak.greedydb.databaseConnections.MongoDBConnectionManager;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class QueryManager {
	
	public DBCursor findJsonPathQuery(String streamName, String timestamp) {
		BasicDBObject whereQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("streamName", streamName));
		obj.add(new BasicDBObject("timestamp", Integer.parseInt(timestamp)));
		whereQuery.put("$and", obj);
		return MongoDBConnectionManager.databaseCollection().find(whereQuery);
	}
	
    public DBCursor allStreamNameQuery(String streamName) {
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("streamName", streamName);
	    return MongoDBConnectionManager.databaseCollection().find(whereQuery);
    }
    
    public DBCursor findLatestJsonPathQuery(String streamName) {
    	BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("streamName", streamName);
	    DBCursor cursor = MongoDBConnectionManager.databaseCollection().find(whereQuery);
	    return cursor.sort(new BasicDBObject("timestamp", -1)).limit(1);
    }
}
