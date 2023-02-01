package festival.srv.entity;

import org.bson.Document;

import java.util.Objects;

import static festival.srv.constant.DbKeys.*;

public class Volunteer implements Entity {

	private String id;
	private String firstName;
	private String lastName;
	private String email;

	public Volunteer() { }

	public Volunteer(Document document) {
		this.id = document.getObjectId(ID).toString();
		this.firstName = document.getString(FIRST_NAME);
		this.lastName = document.getString(LAST_NAME);
		this.email = document.getString(EMAIL);
	}

	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
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
		Volunteer volunteer = (Volunteer) o;
		return Objects.equals(id, volunteer.id) && Objects.equals(firstName, volunteer.firstName) && Objects.equals(lastName, volunteer.lastName) && Objects.equals(email, volunteer.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, email);
	}
}
