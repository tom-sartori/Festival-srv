package festival.srv.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Slot {

	private Date startDate;
	private Date endDate;
	private List<String> volunteerRefs;

	public Slot() { }

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<String> getVolunteerRefs() {
		return volunteerRefs;
	}

	public void setVolunteerRefs(List<String> volunteerRefs) {
		this.volunteerRefs = volunteerRefs;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Slot slot = (Slot) o;
		return Objects.equals(startDate, slot.startDate) && Objects.equals(endDate, slot.endDate) && Objects.equals(volunteerRefs, slot.volunteerRefs);
	}

	@Override
	public int hashCode() {
		return Objects.hash(startDate, endDate, volunteerRefs);
	}
}
