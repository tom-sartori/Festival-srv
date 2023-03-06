package festival.srv.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import festival.srv.constant.ApiPaths;
import festival.srv.constant.Roles;
import festival.srv.entity.Zone;
import festival.srv.service.ZoneService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static festival.srv.resource.Util.toJson;

@Path(ApiPaths.ZONE)
@Produces("application/json")
@Consumes("application/json")
public class ZoneResource {

	@Inject
	ZoneService zoneService;


	/**
	 * Create a new zone. The id is generated by the database.
	 * @param jsonBody the json body of the zone. The id is ignored.
	 * @return 201 if the zone is created.
	 */
	@POST
	@RolesAllowed(Roles.ADMIN)
	public Response create(String jsonBody) {
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm").create();
		Zone zone = gson.fromJson(jsonBody, Zone.class);
		zoneService.create(zone);
		return Response.status(201).build();
	}

	/**
	 * Read all zones. If there is no zone, an empty list is returned.
	 * @return 200 with the list of zones.
	 */
	@GET
	public Response read() {
		String json = toJson(zoneService.read());
		return Response.status(200).entity(json).build();
	}

	/**
	 * Read a zone by its id. If the zone does not exist, a 404 is returned.
	 * @param id the id of the zone.
	 * @return 200 with the zone.
	 */
	@GET
	@Path("/id/{id}")
	public Response read(@PathParam("id") String id) {
		String json = toJson(zoneService.read(id));
		return Response.status(200).entity(json).build();
	}

	/**
	 * Update a zone. If the zone does not exist, a 404 is returned.
	 *
	 * @param id the id of the zone.
	 * @param jsonBody the json body of the zone. The id is ignored.
	 * @return 204 if the zone is updated.
	 */
	@PATCH
	@Path("/{id}")
	@RolesAllowed(Roles.ADMIN)
	public Response update(@PathParam("id") String id, String jsonBody) {
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm").create();
		Zone zone = gson.fromJson(jsonBody, Zone.class);
		zoneService.update(id, zone);
		return Response.status(204).build();
	}


	/**
	 * Add a game to a zone. If the zone does not exist, a 404 is returned.
	 *
	 * @param idZone the id of the zone
	 * @param idGame the id of the game
	 * @return 204 if the game is added to the zone.
	 */

	@PATCH
	@Path("/zone-id/{idZone}/game-id/{idGame}")
	@RolesAllowed(Roles.ADMIN)
	public Response addGame(@PathParam("idZone") String idZone, @PathParam("idGame") String idGame){
		zoneService.addGameById(idZone, idGame);
		return Response.status(204).build();
	}

	/**
	 * Add a volunteer to a zone for a slot. If the slot does not exist, the slot is created. If the zone does not exist, a 404 is returned.
	 *
	 * @param idZone the id of the zone
	 * @param idVolunteer the id of the volunteer
	 * @param jsonBody contains the start date and the end date of the slot
	 * @return 204 if the volunteer is added to the zone for a slot.
	 */

	@PATCH
	@Path("/zone-id/{idZone}/volunteer-id/{idVolunteer}")
	@RolesAllowed(Roles.ADMIN)
	public Response addVolunteerSlot(@PathParam("idZone") String idZone, @PathParam("idVolunteer") String idVolunteer, String jsonBody){
		zoneService.addVolunteerSlot(idZone, idVolunteer, jsonBody);
		return Response.status(204).build();
	}

	/**
	 * Delete a zone.
	 *
	 * @param id the id of the zone.
	 * @return 204 if the zone is deleted.
	 */
	@DELETE
	@Path("/{id}")
	@RolesAllowed(Roles.ADMIN)
	public Response delete(@PathParam("id") String id) {
		zoneService.delete(id);
		return Response.status(204).build();
	}
}
