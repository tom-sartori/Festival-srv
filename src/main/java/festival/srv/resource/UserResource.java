package festival.srv.resource;

import static festival.srv.constant.ApiPaths.*;
import festival.srv.entity.User;
import festival.srv.repository.UserRepository;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path(USER)
public class UserResource {

	private final UserRepository userRepository;

	@Inject
	public UserResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GET
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

	@DELETE
	@Path("/{id}")
	public void deleteUser(@PathParam("id") String id) {
		User user = userRepository.findById(new ObjectId(id));
		userRepository.delete(user);
	}
}
