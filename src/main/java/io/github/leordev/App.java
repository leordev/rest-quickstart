package io.github.leordev;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.constructor.SafeConstructor;
import io.github.leordev.server.Configuration;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws Exception {

        // initialize configuration
        Configuration configuration = initializeConfiguration(args);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(configuration.getPort());
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
                EntryPoint.class.getCanonicalName());

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }

    /**
     * Load the configuration and initialize Config-Related Objects
     * @param args
     * @return
     * @throws IOException
     */
    private static Configuration initializeConfiguration(String[] args) throws IOException {
        Configuration configuration;

        if(args == null || args.length < 1) {
            configuration = new Configuration();
            configuration.initialize();
        } else {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            configuration = mapper.readValue(new File(args[0]), Configuration.class);
        }

        return configuration;
    }
}
