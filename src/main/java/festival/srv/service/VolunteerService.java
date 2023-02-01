package festival.srv.service;

import festival.srv.entity.Volunteer;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static festival.srv.constant.DbCollections.VOLUNTEERS_COLLECTION;
import static festival.srv.constant.DbKeys.*;

@ApplicationScoped
public class VolunteerService extends Service<Volunteer> {


	/**
	 * Create a volunteer in the collection. The id is automatically generated by the database.
	 *
	 * @param volunteer The volunteer to create.
	 */
	@Transactional
	public void create(Volunteer volunteer){
		Document document = new Document()
				.append(FIRST_NAME, volunteer.getFirstName())
				.append(LAST_NAME, volunteer.getLastName())
				.append(EMAIL, volunteer.getEmail());

		super.create(document);
	}

	/**
	 * Get all volunteers from the collection.
	 *
	 * @return The list of volunteers.
	 */
	public List<Volunteer> read(){
		return getDocumentList().stream().map(Volunteer::new).collect(Collectors.toList());
	}

	/**
	 * Get a volunteer from the collection.
	 *
	 * @param id The id of the volunteer.
	 * @return The volunteer.
	 */
	public Volunteer read(String id){
		return new Volunteer(getDocument(id));
	}

	@Override
	protected String getCollectionName() {
		return VOLUNTEERS_COLLECTION;
	}
}
