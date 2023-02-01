package festival.srv.getting.started;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    @GET
    @Path("greeting/{name}")
    public String greeting(@PathParam("name") String name) {
        return greetingService.greeting(name);
    }
}
