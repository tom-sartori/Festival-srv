package festival.srv.entity;

import org.bson.Document;

import java.util.Objects;

import static festival.srv.constant.DbKeys.*;

public class Fruit implements Entity {

	private String id;
	private String name;
	private String description;

	public Fruit() { }

	public Fruit(Document document) {
		this.id = document.getObjectId(ID).toString();
		this.name = document.getString(NAME);
		this.description = document.getString(DESCRIPTION);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Fruit fruit = (Fruit) o;
		return Objects.equals(id, fruit.id) && Objects.equals(name, fruit.name) && Objects.equals(description, fruit.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description);
	}
}
