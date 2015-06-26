package com.test.server;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.test.datadictreader.DataDictMain;

/**
 * Created by siva-2356 on 17/6/15.
 */
@JsonAutoDetect
@Path("/json")
public class Test {
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response test() {
        String json = "{'status' : 200}";
        return Response.ok(json).build();
    }

    @GET
    @Path("/tablelist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response tableList() {
        DataDictMain dataDictMain = new DataDictMain();
        String json = dataDictMain.xmlReader().toString();
        return Response.ok(json).build();
    }
}
