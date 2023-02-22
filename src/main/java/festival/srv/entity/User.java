package festival.srv.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

import java.util.Objects;

import static festival.srv.constant.DbCollections.USER_COLLECTION;

@MongoEntity(collection = USER_COLLECTION)
public class User {

	private ObjectId id;
	private String firstName;
	private String lastName;
	private String email;

	public User() { }

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, email);
	}
}
