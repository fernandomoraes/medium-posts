package com.moraes;

import com.moraes.metrics.MyCustomMetrics;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("my-resource")
public class MyApiResource {

    @Inject
    private MyCustomMetrics myCustomMetrics;

    @POST
    public Response execute() {
        myCustomMetrics.incrementFoo();
        return Response.ok().build();
    }

}
