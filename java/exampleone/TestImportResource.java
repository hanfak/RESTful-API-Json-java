package exampleone;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.hanfak.rest.exampleone.resources.ImportResource;
import org.junit.Test;

public class TestImportResource extends JerseyTest{
    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(ImportResource.class);
    }
    
//    @Test
//    public void testFetchAllClicks() {
//        Response output = target("/import/click").request().get();
//        assertEquals("should return status 200", 200, output.getStatus());
//        assertNotNull("Should return list", output.getEntity());
//    }
//    
//    @Test
//    public void testFetchAllEmployers() {
//        Response output = target("/import/employer").request().get();
//        assertEquals("should return status 200", 200, output.getStatus());
//        assertNotNull("Should return list", output.getEntity());
//    }

    @Test
    public void test() {
        final String response = target("/import/click").request().get(String.class);
        Response output = target("/import/click").request().get();
//        System.out.println(output.getMediaType());
        assertThat(response, containsString("1460442540"));
        
    }
}
