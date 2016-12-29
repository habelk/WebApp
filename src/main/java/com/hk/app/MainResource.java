package com.hk.app;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

@Path("/v1")
public class MainResource { // implements JAXRSResource {

    private final ObjectMapper objectMapper;

    @Inject
    public MainResource(final ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/webapp/")
    public Response retrieveIBusPreferences(@QueryParam("name") final String name) {

        try {
            return Response.ok(objectMapper.writeValueAsString("hello :" + name), MediaType.APPLICATION_JSON).build();
        } catch (final IOException e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}