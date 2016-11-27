package com.hanfak.greedydb.tests.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.hanfak.greedydb.controllers.ImportController;

public class ImportControllerTest extends JerseyTest{
	@Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(ImportController.class);
    }
	
	@Test
	public void ClicksPathWorking() {
		Response output = target("/import/click").request().get();
		assertEquals("should return status 200", 200, output.getStatus());
//		assertEquals( "application/json", output.getMediaType().toString());
//		assertNotNull("Should return list", output.getEntity());
	}
  
	@Test
	public void ClicksReturnJson() {
		final String output = target("/import/click").request().get(String.class);
		assertThat(output, containsString("1460442540"));
	}
	
	@Test
	public void EmployersPathWorking() {
		Response output = target("/import/employer").request().get();
		assertEquals("should return status 200", 200, output.getStatus());
//		assertEquals( "application/json", output.getMediaType().toString());
//		assertNotNull("Should return list", output.getEntity());
	}
	
	@Test
	public void EmployersReturnJson() {
		final String output = target("/import/employer").request().get(String.class);
		assertThat(output, containsString("1460463641"));
	}
}
