package festival.srv.service;

import festival.srv.entity.Game;
import festival.srv.entity.Zone;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static festival.srv.constant.DbCollections.GAMES_COLLECTION;
import static festival.srv.constant.DbKeys.NAME;
import static festival.srv.constant.DbKeys.TYPE;

@ApplicationScoped
public class GameService extends Service<Game> {

	@Inject
	ZoneService zoneService;

	/**
	 * Create a game in the collection. The id is automatically generated by the database.
	 *
	 * @param game The game to create.
	 */
	@Transactional
	public void create(Game game){
		Document document = new Document()
				.append(NAME, game.getName())
				.append(TYPE, game.getType());

		super.create(document);
	}

	/**
	 * Get all games from the collection.
	 *
	 * @return The list of games.
	 */
	public List<Game> read(){
		return getDocumentList().stream().map(Game::new).collect(Collectors.toList());
	}

	/**
	 * Get all games with the same name.
	 *
	 * @param name The name of the game.
	 * @return The list of games.
	 */
	public List<Game> readByName(String name) {
		return getDocumentList().stream()
				.filter(document -> document.getString(NAME).toLowerCase().matches("(.*)" + name.toLowerCase() + "(.*)"))
				.map(Game::new)
				.collect(Collectors.toList());
	}

	/**
	 * Get all games with the same type.
	 *
	 * @param type The type of the game.
	 * @return The list of games.
	 */
	public List<Game> readByType(String type){
		return getDocumentList().stream()
				.filter(document -> document.getString(TYPE).toLowerCase().matches("(.*)" + type.toLowerCase() + "(.*)"))
				.map(Game::new)
				.collect(Collectors.toList());
	}

	/**
	 * Get all games in the same zone.
	 *
	 * @param zoneId The zone of the game.
	 * @return The list of games.
	 */
	public List<Game> readByZone(String zoneId){
		Zone zone = zoneService.read(zoneId);
		if (zone == null) {
			throw new NotFoundException("Zone not found");
		}

		return zone.getGameRefs().stream().map(this::read).collect(Collectors.toList());
	}

	/**
	 * Get a game from the collection.
	 *
	 * @param id The id of the game.
	 * @return The game.
	 */
	public Game read(String id){
		return new Game(getDocument(id));
	}

	@Override
	protected String getCollectionName() {
		return GAMES_COLLECTION;
	}
}
