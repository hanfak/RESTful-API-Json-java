package com.hanfak.greedydb.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hanfak.greedydb.databaseServices.ImportDatabaseService;
import com.hanfak.greedydb.models.Click;


@Path("/import")
public class ImportController {
	ImportDatabaseService databaseService = new ImportDatabaseService();

	@GET
	@Path("/click")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Click> getClicks() {
		return databaseService.getClickObjects();
	}
}
