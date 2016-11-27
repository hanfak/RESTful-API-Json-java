package com.hanfak.greedydb.tests.controllers;

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
  public void testFetchAllClicks() {
      Response output = target("/import/click").request().get();
      assertEquals("should return status 200", 200, output.getStatus());
  }
}
