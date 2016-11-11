package io.github.leordev;

import io.github.leordev.server.Configuration;
import io.github.leordev.server.SystemResource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws Exception {

        // initialize configuration
        initializeConfiguration(args);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(Configuration.getInstance().getPort());
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
                SystemResource.class.getCanonicalName());

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
    private static void initializeConfiguration(String[] args) throws IOException {
        Configuration configuration = Configuration.getInstance();

        if(args == null || args.length < 1) {
            configuration.initialize();
        } else {
            configuration.loadYaml(args[0]);
        }
    }
}
