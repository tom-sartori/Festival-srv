package festival.srv.entity;

import org.bson.Document;

import java.util.Objects;

import static festival.srv.constant.MongoDbConstants.*;

public class Game implements Entity {

	private String id;
	private String name;
	private String type;

	public Game() { }

	public Game(Document document) {
		this.id = document.getObjectId(ID).toString();
		this.name = document.getString(NAME);
		this.type = document.getString(TYPE);
	}

	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Game game = (Game) o;
		return Objects.equals(id, game.id) && Objects.equals(name, game.name) && Objects.equals(type, game.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, type);
	}
}
