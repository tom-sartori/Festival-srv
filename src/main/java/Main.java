import festival.srv.entity.Game;
import festival.srv.entity.Slot;
import festival.srv.entity.Volunteer;
import festival.srv.entity.Zone;
import festival.srv.resource.GameResource;
import festival.srv.resource.VolunteerResource;
import festival.srv.resource.ZoneResource;
import festival.srv.service.GameService;
import festival.srv.service.VolunteerService;
import festival.srv.service.ZoneService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static festival.srv.resource.Util.toJson;

public class Main {
	public static void main(String... args) {
		Quarkus.run(MyApp.class, args);
	}

	public static class MyApp implements QuarkusApplication {

		@Inject
		VolunteerResource volunteerResource;

		@Inject
		VolunteerService volunteerService;

		@Inject
		GameResource gameResource;

		@Inject
		GameService gameService;

		@Inject
		ZoneService zoneService;


		@Override
		public int run(String... args) throws Exception {
			System.out.println("Do startup logic here");

			List<Volunteer> volunteerList = new ArrayList<>();
			volunteerList.add(new Volunteer("John", "Doe", "jd@gmail.com"));
			volunteerList.add(new Volunteer("Jane", "Doe", "jde@gmail.com"));
			volunteerList.add(new Volunteer("Michel", "Dubois", "db@gmail.com"));
			volunteerList.add(new Volunteer("Jean", "Dupont", "dj@gmail.com"));
			volunteerList.add(new Volunteer("Pierre", "Durand", "pd@gmail.com"));
			volunteerList.add(new Volunteer("Lalli", "Masse", "lm@gmail.com"));
			volunteerList.forEach(volunteer -> volunteerResource.create(toJson(volunteer)));

			// Set a list of board games of type : CHILD, FAMILY, AMBIANCE, INITIATED, EXPERT
			List<Game> gameList = new ArrayList<>();
			gameList.add(new Game("Labyrinthe", "CHILD"));
			gameList.add(new Game("Loup-Garou", "CHILD"));
			gameList.add(new Game("Uno", "CHILD"));
			gameList.add(new Game("Blokus", "CHILD"));
			gameList.add(new Game("Dixit", "CHILD"));
			gameList.add(new Game("Coloretto", "CHILD"));
			gameList.add(new Game("Carcassonne Junior", "CHILD"));
			gameList.add(new Game("Bananagrams", "CHILD"));
			gameList.add(new Game("Blokus Trigon", "CHILD"));

			// Set a lif of board games of type FAMILY
			gameList.add(new Game("Monopoly", "FAMILY"));
			gameList.add(new Game("Risk", "FAMILY"));
			gameList.add(new Game("Ludo", "FAMILY"));
			gameList.add(new Game("Cluedo", "FAMILY"));
			gameList.add(new Game("Jeu de l'oie", "FAMILY"));
			gameList.add(new Game("Scrabble", "FAMILY"));
			gameList.add(new Game("Mousetrap", "FAMILY"));
			gameList.add(new Game("Jeu de cartes", "FAMILY"));
			gameList.add(new Game("Boggle", "FAMILY"));
			gameList.add(new Game("Mille Bornes", "FAMILY"));
			gameList.add(new Game("Blokus Duo", "FAMILY"));

			// Set a list of board games of type AMBIANCE
			gameList.add(new Game("Cocktail", "AMBIANCE"));
			gameList.add(new Game("Codenames", "AMBIANCE"));
			gameList.add(new Game("Pictionary", "AMBIANCE"));
			gameList.add(new Game("Zombie Dice", "AMBIANCE"));
			gameList.add(new Game("The Resistance", "AMBIANCE"));
			gameList.add(new Game("Dobble", "AMBIANCE"));

			// Set a list of board games of type INITIATED
			gameList.add(new Game("Ticket to Ride", "INITIATED"));
			gameList.add(new Game("Splendor", "INITIATED"));
			gameList.add(new Game("7 Wonders", "INITIATED"));
			gameList.add(new Game("Mysterium", "INITIATED"));
			gameList.add(new Game("The Mind", "INITIATED"));
			gameList.add(new Game("Carcassonne", "INITIATED"));
			gameList.add(new Game("Poker", "INITIATED"));

			// Set a list of board games of type EXPERT
			gameList.add(new Game("Catan", "EXPERT"));
			gameList.add(new Game("Pandemic", "EXPERT"));
			gameList.add(new Game("Kingdomino", "EXPERT"));
			gameList.add(new Game("Munchkin", "EXPERT"));

			// Create games
			gameList.forEach(game -> gameResource.create(toJson(game)));



			List<Game> allGames = gameService.read();
			List<Volunteer> allVolunteers = volunteerService.read();

			List<String> gameRefList = new ArrayList<>();
			gameRefList.add(allGames.get(0).getId());
			gameRefList.add(allGames.get(1).getId());
			gameRefList.add(allGames.get(2).getId());

			List<String> volunteerRefList = new ArrayList<>();
			volunteerRefList.add(allVolunteers.get(0).getId());
			volunteerRefList.add(allVolunteers.get(1).getId());

			List<Slot> slotList = new ArrayList<>();
			slotList.add(new Slot(new Date(), new Date(), volunteerRefList));

			zoneService.create(new Zone("Esplanade-droite", gameRefList, slotList));

			System.out.println("Settlement finished. ");

			Quarkus.waitForExit();
			return 0;
		}
	}
}
