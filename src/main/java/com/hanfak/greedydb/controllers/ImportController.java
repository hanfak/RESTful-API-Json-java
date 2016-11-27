package com.hanfak.greedydb.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.hanfak.greedydb.databaseServices.ImportDatabaseService;
import com.hanfak.greedydb.models.Click;
import com.hanfak.greedydb.models.Employer;


@Path("/import")
public class ImportController {
	ImportDatabaseService databaseService = new ImportDatabaseService();

	@GET
	@Path("/click")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Click> getClicks() {
		return databaseService.getClickObjects();
	}
	
	@GET
	@Path("/employer")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employer> getEmployees() {
		return databaseService.getEmployerObjects();
	}
	
//  Tried to use variable pathparams, and return response, but cannot
//	solve the error. It is getting the data from db and storing in model, but
//	does not turn to json
//	@GET
//	@Path("/{streamName}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getObjects(
//			@PathParam("streamName") String streamName) {
//		System.out.println((String) streamName == "click");
//		if(streamName.equals("click")){
//			System.out.println("click");
//			List<Click> clicks = databaseService.getClicks();
//			System.out.println(clicks.get(0).getTimestamp());
//			return Response.ok(clicks).build();
//		}
//		if(streamName.equals("employer")){
//			System.out.println("employer");
//			List<Employer> employers = databaseService.getEmployerObjects();
//			System.out.println(employers.get(0).getTimestamp());
//			return Response.ok(employers).build();
//		}
//	}
}
