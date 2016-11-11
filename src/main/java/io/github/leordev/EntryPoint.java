package io.github.leordev;

import io.github.leordev.server.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by leo on 11/11/16.
 */
@Path("/entry-point")
public class EntryPoint {

    Logger log = LoggerFactory.getLogger(EntryPoint.class);

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public Configuration test() {
        log.info("Entry Point test");
        return new Configuration();
    }
}
