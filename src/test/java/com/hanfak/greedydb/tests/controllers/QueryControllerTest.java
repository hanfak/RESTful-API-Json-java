package com.hanfak.greedydb.tests.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.hanfak.greedydb.controllers.QueryController;

public class QueryControllerTest extends JerseyTest {
	@Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(QueryController.class);
    }
	
	@Test
	public void specificPathWorking() {
		Response output = target("/query/click/1460442540/origin/band").request().get();
		assertEquals("should return status 200", 200, output.getStatus());
	}
	
	@Test
	public void specificPathReturnValue() {
		final String output = target("/query/clicks/1460422614/origin/brand").request().get(String.class);
		assertThat(output, containsString("hcom"));
	}
	
	@Test
	public void latestPathReturnValueForEmployer() {
		final String output = target("/query/employers/latest/id").request().get(String.class);
		assertThat(output, containsString("667732"));
	}
	
	@Test
	public void latestPathReturnValueForClick() {
		final String output = target("/query/clicks/latest/page").request().get(String.class);
		assertThat(output, containsString("/profile.html"));
	}
	
	@Test
	public void oldestPathReturnValueForEmployer() {
		final String output = target("/query/employers/oldest/id").request().get(String.class);
		assertThat(output, containsString("1180023"));
	}
	
	@Test
	public void oldestPathReturnValueForClick() {
		final String output = target("/query/clicks/oldest/page").request().get(String.class);
		assertThat(output, containsString("/error.html"));
	}
}
