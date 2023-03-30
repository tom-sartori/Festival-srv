package festival.srv.resource;

import com.google.gson.Gson;
import festival.srv.constant.ApiPaths;
import festival.srv.constant.Roles;
import festival.srv.entity.Volunteer;
import festival.srv.service.VolunteerService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static festival.srv.resource.Util.toJson;

@Path(ApiPaths.VOLUNTEER)
@Produces("application/json")
@Consumes("application/json")
public class VolunteerResource {

	@Inject
	VolunteerService volunteerService;


	/**
	 * Create a new volunteer. The id is generated by the database.
	 * @param jsonBody the json body of the volunteer. The id is ignored.
	 * @return 201 if the volunteer is created.
	 */
	@POST
	public Response create(String jsonBody) {
		Volunteer volunteer = new Gson().fromJson(jsonBody, Volunteer.class);
		volunteerService.create(volunteer);
		return Response.status(201).build();
	}

	/**
	 * Read all volunteers. If there is no volunteer, an empty list is returned.
	 * @return 200 with the list of volunteers.
	 */
	@GET
	@PermitAll
	public Response read() {
		String json = toJson(volunteerService.read());
		return Response.status(200).entity(json).build();
	}

	/**
	 * Read all volunteers by lastname. If there is no volunteer, an empty list is returned.
	 * @param lastname the name of the volunteer.
	 * @return 200 with the list of volunteers.
	 */
	@GET
	@Path("/name/{name}")
	@PermitAll
	public Response readByLastName(@PathParam("name") String lastname) {
		String json = toJson(volunteerService.readByLastName(lastname));
		return Response.status(200).entity(json).build();
	}

	/**
	 * Read all volunteers by firstname. If there is no volunteer, an empty list is returned.
	 * @param firstname the firstname of the volunteer.
	 * @return 200 with the list of volunteers.
	 */
	@GET
	@Path("/firstname/{firstname}")
	@PermitAll
	public Response readByFirstName(@PathParam("firstname") String firstname) {
		String json = toJson(volunteerService.readByFirstName(firstname));
		return Response.status(200).entity(json).build();
	}

	/**
	 * Read all volunteers by firstname or lastname. If there is no volunteer, an empty list is returned.
	 * @param name the firstname/lastname of the volunteer.
	 * @return 200 with the list of volunteers.
	 */

	@GET
	@Path("/firstname-lastname/{name}")
	@PermitAll
	public Response readByFirstNameOrLastName(@PathParam("name") String name){
		String json = toJson(volunteerService.readByFirstNameOrLastName(name));
		System.out.println(json);
		return Response.status(200).entity(json).build();

	}

	/**
	 * Read all volunteers by slot for a given zone. If there is no volunteer, an empty list is returned.
	 *
	 * @return 200 with the list of volunteers.
	 */
	@GET
	@Path("/zone-id/{zone-id}")
	@PermitAll
	public Response readBySlotForOneZoneId(@PathParam("zone-id") String zoneId) {
		String json = toJson(volunteerService.readBySlotForOneZoneId(zoneId));
		return Response.status(200).entity(json).build();
	}

	/**
	 * Read a volunteer by its id. If the volunteer does not exist, a 404 is returned.
	 * @param id the id of the volunteer.
	 * @return 200 with the volunteer.
	 */
	@GET
	@Path("/id/{id}")
	@PermitAll
	public Response read(@PathParam("id") String id) {
		String json = toJson(volunteerService.read(id));
		return Response.status(200).entity(json).build();
	}

	/**
	 * Update a volunteer. If the volunteer does not exist, a 404 is returned.
	 *
	 * @param id the id of the volunteer.
	 * @param jsonBody the json body of the volunteer. The id is ignored.
	 * @return 204 if the volunteer is updated.
	 */
	@PATCH
	@Path("/{id}")
	public Response update(@PathParam("id") String id, String jsonBody) {
		Volunteer volunteer = new Gson().fromJson(jsonBody, Volunteer.class);
		volunteerService.update(id, volunteer);
		return Response.status(204).build();
	}

	/**
	 * Delete a volunteer.
	 *
	 * @param id the id of the volunteer.
	 * @return 204 if the volunteer is deleted.
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		volunteerService.delete(id);
		return Response.status(204).build();
	}
}
