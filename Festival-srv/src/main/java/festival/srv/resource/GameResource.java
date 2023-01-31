package festival.srv.resource;

import com.google.gson.Gson;
import festival.srv.constant.ApiPaths;
import festival.srv.entity.Game;
import festival.srv.service.GameService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static festival.srv.resource.Util.toJson;

@Path(ApiPaths.GAME)
@Produces("application/json")
@Consumes("application/json")
public class GameResource {

	@Inject
	GameService gameService;


	/**
	 * Create a new game. The id is generated by the database.
	 * @param jsonBody the json body of the game. The id is ignored.
	 * @return 201 if the game is created.
	 */
	@POST
	public Response create(String jsonBody) {
		Game game = new Gson().fromJson(jsonBody, Game.class);
		gameService.create(game);
		return Response.status(201).build();
	}

	/**
	 * Read all games. If there is no game, an empty list is returned.
	 * @return 200 with the list of games.
	 */
	@GET
	public Response read() {
		String json = toJson(gameService.read());
		return Response.status(200).entity(json).build();
	}

	/**
	 * Read all games by name. If there is no game, an empty list is returned.
	 * @param name the name of the game.
	 * @return 200 with the list of games.
	 */
	@GET
	@Path("/name/{name}")
	public Response readByName(@PathParam("name") String name) {
		String json = toJson(gameService.readByName(name));
		return Response.status(200).entity(json).build();
	}

	/**
	 * Read all games by type. If there is no game, an empty list is returned.
	 *
	 * @param type the type of the game.
	 * @return 200 with the list of games.
	 */
	@GET
	@Path("/type/{type}")
	public Response readByType(@PathParam("type") String type) {
		String json = toJson(gameService.readByType(type));
		return Response.status(200).entity(json).build();
	}

	/**
	 * Read all games by zone. If there is no game, an empty list is returned.
	 *
	 * @param zone the zone of the game.
	 * @return 200 with the list of games.
	 */
	@GET
	@Path("/zone/{zone}")
	public Response readByZone(@PathParam("zone") String zone) {
		String json = toJson(gameService.readByZone(zone));
		return Response.status(200).entity(json).build();
	}

	/**
	 * Read a game by its id. If the game does not exist, a 404 is returned.
	 * @param id the id of the game.
	 * @return 200 with the game.
	 */
	@GET
	@Path("/id/{id}")
	public Response read(@PathParam("id") String id) {
		String json = toJson(gameService.read(id));
		return Response.status(200).entity(json).build();
	}

	/**
	 * Update a game. If the game does not exist, a 404 is returned.
	 *
	 * @param id the id of the game.
	 * @param jsonBody the json body of the game. The id is ignored.
	 * @return 204 if the game is updated.
	 */
	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") String id, String jsonBody) {
		Game game = new Gson().fromJson(jsonBody, Game.class);
		gameService.update(id, game);
		return Response.status(204).build();
	}

	/**
	 * Delete a game.
	 *
	 * @param id the id of the game.
	 * @return 204 if the game is deleted.
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		gameService.delete(id);
		return Response.status(204).build();
	}
}
