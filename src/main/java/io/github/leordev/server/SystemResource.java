package io.github.leordev.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by leo on 11/11/16.
 */
@Path("/system")
public class SystemResource {

    private Logger log = LoggerFactory.getLogger(SystemResource.class);

    private Configuration configuration = Configuration.getInstance();

    @GET
    @Path("/configuration")
    @Produces(MediaType.APPLICATION_JSON)
    public Configuration test() {
        log.error("System Endpoint called");
        return configuration;
    }
}
