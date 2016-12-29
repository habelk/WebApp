package com.hk.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceInitializer extends GuiceServletContextListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                bind(MainResource.class);
                filter("/*").through(GuiceContainer.class); //$NON-NLS-1$
            }
        });
    }
}
