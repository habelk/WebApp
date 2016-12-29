package com.hk.app;

import java.util.EnumSet;

import org.eclipse.jetty.server.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.servlet.GuiceFilter;

/**
 * Description.
 * @author Your Name
 */
public class GuiceInitializerTest {
    /**
     * Starts a server instance to test resources locally
     * @param args args
     * @throws Exception exception
     */
    public static void main(final String[] args) throws Exception {
        final Server server = new Server(8080);

        // Create a servlet context and add the jersey servlet.
        final ServletContextHandler sch = new ServletContextHandler(server, "/test"); //$NON-NLS-1$

        // Add our Guice listener that includes our bindings
        sch.addEventListener(new GuiceInitializer());

        // Then add GuiceFilter and configure the server to
        // reroute all requests through this filter.
        sch.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST)); //$NON-NLS-1$

        // Must add DefaultServlet for embedded Jetty.
        // Failing to do this will cause 404 errors.
        sch.addServlet(DefaultServlet.class, "/"); //$NON-NLS-1$

        // Start the server
        server.start();
        server.join();
    }
}
