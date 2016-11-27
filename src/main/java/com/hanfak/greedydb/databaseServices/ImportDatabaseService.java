package com.hanfak.greedydb.databaseServices;

import java.util.ArrayList;
import java.util.List;

import com.hanfak.greedydb.models.Click;
import com.hanfak.greedydb.models.Employer;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ImportDatabaseService {
	private ArrayList<Click> clicks  = new ArrayList<Click>();
	private ArrayList<Employer> employers  = new ArrayList<Employer>();
	
	public  List<Click> getClickObjects() {
	   try{   
		     DBCursor cursor = allStreamNameQuery(DatabaseCollection(), "clicks");
		     listOfClickObjects(clicks, cursor);
		     cursor.close();
	         return clicks;
       } catch(Exception e) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	   return clicks;
	}
	
	public  List<Employer> getEmployerObjects() {
	   try{   
		     DBCursor cursor = allStreamNameQuery(DatabaseCollection(), "employers");
		     listOfEmployerObjects(employers, cursor);
		     cursor.close();
	         return employers;
       } catch(Exception e) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	   return employers;
	}
	
    private List<Click> listOfClickObjects(ArrayList<Click> clicks, DBCursor cursor){
		 while (cursor.hasNext()) { 
			 Click clickStream = new Click();
			 Click.Origin origin  = new Click.Origin();
			 DBObject obj = cursor.next();
			 storeClickObject(clickStream, origin, obj);
			 clicks.add(clickStream);
		 }
		 return clicks;
	}
	
    private List<Employer> listOfEmployerObjects(ArrayList<Employer> employers, DBCursor cursor){
		 while (cursor.hasNext()) { 
			 Employer employerStream = new Employer();
			 DBObject obj = cursor.next();
			 storeEmployerObject(employerStream, obj);
			 employers.add(employerStream);
		 }
		 return employers;
	}
    
    private DBCursor allStreamNameQuery(DBCollection collection, String streamName) {
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("streamName", streamName);
	    return collection.find(whereQuery);
    }
    
    private DBCollection DatabaseCollection() {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
        DB db = mongoClient.getDB( "test" );
        System.out.println("Connect to database successfully");

        DBCollection collection = db.getCollection("streams");
        System.out.println("Collection mycol selected successfully");
       
        return collection;
     }
    
	 private void storeClickObject(Click click, Click.Origin origin, DBObject obj) {
		 click.setPage((String) obj.get("page"));
		 click.setStreamName((String) obj.get("streamName"));
		 click.setTimestamp(String.format("%.0f", obj.get("timestamp")));
		 click.setOrigin(origin);
		 click.getOrigin().setBrand((String) ((DBObject) obj.get("origin")).get("brand"));
		 click.getOrigin().setPost((String) ((DBObject) obj.get("origin")).get("post"));
	 }
	 
	 private void storeEmployerObject(Employer employer, DBObject obj) {
		 employer.setId((String) obj.get("id"));
		 employer.setName((String) obj.get("name"));
		 employer.setSurname((String) obj.get("surname"));
	     employer.setStreamName((String) obj.get("streamName"));
	     employer.setTimestamp(String.format("%.0f", obj.get("timestamp")));
	 }
}
