package festival.srv.dto;

import festival.srv.entity.Slot;
import festival.srv.entity.Volunteer;

import java.util.List;
import java.util.Objects;

public class SlotVolunteerDto {

	private String zoneId;
	private Slot slot;
	private List<Volunteer> volunteers;

	public SlotVolunteerDto() { }

	public SlotVolunteerDto(String zoneId, Slot slot, List<Volunteer> volunteers) {
		this.zoneId = zoneId;
		this.slot = slot;
		this.volunteers = volunteers;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public List<Volunteer> getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SlotVolunteerDto that = (SlotVolunteerDto) o;
		return Objects.equals(zoneId, that.zoneId) && Objects.equals(slot, that.slot) && Objects.equals(volunteers, that.volunteers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(zoneId, slot, volunteers);
	}
}
