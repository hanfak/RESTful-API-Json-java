package com.hanfak.greedydb.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/import")
public class ImportController {
	@GET
	@Path("/click")
	@Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
        return "Click json object";
    }
}
