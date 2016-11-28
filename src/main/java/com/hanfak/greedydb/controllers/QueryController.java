package com.hanfak.greedydb.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hanfak.greedydb.databaseServices.QueryDatabaseService;


@Path("/query")
public class QueryController {
	QueryDatabaseService databaseService = new QueryDatabaseService();
	
	@GET
	@Path("/{streamName}/{timestamp}/{jsonPath:.*}")
	@Produces(MediaType.TEXT_PLAIN)
	public String showSpecficField(
			@PathParam("streamName") String streamName,
			@PathParam("timestamp") String timestamp,
			@PathParam("jsonPath") String jsonPath
			) {
		return databaseService.getFieldValue(streamName, timestamp, jsonPath);
	}
	
	@GET
	@Path("/{streamName}/latest/{jsonPath:.*}")
	@Produces(MediaType.TEXT_PLAIN)
	public String showLatestField(
			@PathParam("streamName") String streamName,
			@PathParam("jsonPath") String jsonPath
			) {
		return databaseService.getLatestValue(streamName, jsonPath);
	}
	
	@GET
	@Path("/{streamName}/oldest/{jsonPath:.*}")
	@Produces(MediaType.TEXT_PLAIN)
	public String showOldestField(
			@PathParam("streamName") String streamName,
			@PathParam("jsonPath") String jsonPath
			) {
		return databaseService.getOldestValue(streamName, jsonPath);
	}
}
