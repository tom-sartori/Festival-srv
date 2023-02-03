package festival.srv.entity;

import org.bson.Document;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static festival.srv.constant.DbKeys.*;

public class Zone implements Entity {

	private String id;
	private String name;
	private List<String> gameRefs;
	private List<Slot> slots;


	public Zone() { }

	public Zone(Document document) {
		this.id = document.getObjectId(ID).toString();
		this.name = document.getString(NAME);
		this.gameRefs = document.getList(GAME_REFS, String.class);
		this.slots = document.getList(SLOTS, Document.class).stream()
				.map(Slot::new)
				.collect(Collectors.toList());
	}

	public Zone(String name, List<String> gameRefs, List<Slot> slots) {
		this.name = name;
		this.gameRefs = gameRefs;
		this.slots = slots;
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

	public List<String> getGameRefs() {
		return gameRefs;
	}

	public void setGameRefs(List<String> gameRefs) {
		this.gameRefs = gameRefs;
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Zone zone = (Zone) o;
		return Objects.equals(id, zone.id) && Objects.equals(name, zone.name) && Objects.equals(gameRefs, zone.gameRefs) && Objects.equals(slots, zone.slots);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, gameRefs, slots);
	}
}
