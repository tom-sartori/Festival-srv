package festival.srv.repository;

import festival.srv.entity.User;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {


	/**
	 * Find a user by email.
	 *
	 * @param email The email of the user.
	 * @return The user or null if not found.
	 */
	public User findByEmail(String email) {
		return find("email", email).firstResult();
	}

	/**
	 * Register a new user. If the user already exists, return a CONFLICT response.
	 *
	 * @param user The user to register. The id is ignored and an email is required.
	 * @return A created response if the user was registered, a conflict response if the user already exists.
	 */
	public Response register(User user) {
		if (user.getEmail() == null) {
			// The user does not have an email.
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("User does not have an email. ")
					.build();
		}
		else if (findByEmail(user.getEmail()) != null) {
			// The user already exists.
			return Response.status(Response.Status.CONFLICT)
					.entity("User already exists. ")
					.build();
		}
		else {
			// The user does not exist.
			user.setId(null);
			persist(user);
			return Response.status(Response.Status.CREATED).build();
		}
	}
}
