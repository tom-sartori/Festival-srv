package festival.srv.resource;

import festival.srv.service.JwtService;

import static festival.srv.constant.ApiPaths.JWT;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(JWT)
@ApplicationScoped
public class JwtResource {

	@Inject
	JwtService jwtService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getJwt() {
//		String jwt = jwtService.generateJwt();
//		return Response.ok(jwt).build();

		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
}
