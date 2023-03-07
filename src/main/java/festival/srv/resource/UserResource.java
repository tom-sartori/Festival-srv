package festival.srv.resource;

import static festival.srv.constant.ApiPaths.*;

import festival.srv.constant.Roles;
import festival.srv.entity.User;
import festival.srv.repository.UserRepository;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@SecurityScheme(
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT"
)
@Path(USER)
@ApplicationScoped
public class UserResource {

	private final UserRepository userRepository;

	@Inject
	public UserResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GET
	@RolesAllowed(Roles.ADMIN)
	public List<User> getUsers() {
		return userRepository.listAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") String id) {
		return userRepository.findById(new ObjectId(id));
	}

	@PUT
	@Path("/{id}")
	@RolesAllowed(Roles.ADMIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateUser(@PathParam("id") String id, User user) {
		user.setId(new ObjectId(id));
		userRepository.update(user);
	}

	@POST
	@Path(REGISTER)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(User user) {
		return userRepository.register(user);
	}

	@POST
	@Path(LOGIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		return userRepository.login(user);
	}

	@DELETE
	@Path("/{id}")
	@RolesAllowed(Roles.ADMIN)
	public void deleteUser(@PathParam("id") String id) {
		User user = userRepository.findById(new ObjectId(id));
		userRepository.delete(user);
	}
}
