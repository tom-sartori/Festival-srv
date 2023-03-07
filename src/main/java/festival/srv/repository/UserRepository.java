package festival.srv.repository;

import festival.srv.entity.User;
import festival.srv.service.JwtService;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {

	@Inject
	JwtService jwtService;

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
					.entity("User must provide an email. ")
					.build();
		}
		else if (user.getPassword() == null) {
			// The user does not have a password.
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("User must provide a password. ")
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
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(15)));
			persist(user);
			return Response.status(Response.Status.CREATED).build();
		}
	}

	public Response login(User user) {
		if (user.getEmail() == null || user.getPassword() == null) {
			// The user does not have an email.
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity("Bad credentials. ")
					.build();
		}
		else {
			User supposedUser = findByEmail(user.getEmail());
			if (supposedUser == null) {
				// The user does not exist.
				return Response.status(Response.Status.UNAUTHORIZED)
						.entity("Bad credentials. ")
						.build();
			}
			else {
				// The user exists.
				if (BCrypt.checkpw(user.getPassword(), supposedUser.getPassword())) {
					// The password is correct.
					return Response
							.status(Response.Status.OK)
//							.entity(jwtService.generateJwt())
							.entity("{ \"token\": \"" + jwtService.generateJwt() + "\" }")
							.build();
				}
				else {
					// The password is wrong.
					return Response.status(Response.Status.UNAUTHORIZED)
							.entity("Bad credentials. ")
							.build();
				}
			}
		}
	}
}
