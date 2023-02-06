import festival.srv.entity.Game;
import festival.srv.entity.Slot;
import festival.srv.entity.Volunteer;
import festival.srv.entity.Zone;
import festival.srv.resource.GameResource;
import festival.srv.resource.VolunteerResource;
import festival.srv.resource.ZoneResource;
import festival.srv.service.GameService;
import festival.srv.service.VolunteerService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		ZoneResource zoneResource;

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

				// type : enfant, famille, ambiance, initié, expert
				List<Game> gameList = new ArrayList<>();
				gameList.add(new Game("Poker", "expert"));
				gameList.add(new Game("Dames", "ambiance"));
				gameList.add(new Game("Domino", "enfant"));
				gameList.add(new Game("Uno", "enfant"));
				gameList.add(new Game("Bingo", "famille"));
				gameList.add(new Game("Mémoire", "enfant"));
				gameList.add(new Game("Puissance 4", "enfant"));
				gameList.add(new Game("Morpion", "enfant"));
				gameList.add(new Game("Solitaire", "initié"));
				gameList.add(new Game("Tarot", "expert"));
				gameList.add(new Game("Mahjong", "expert"));
				gameList.add(new Game("Monopoly", "famille"));
				gameList.add(new Game("Risk", "expert"));
				gameList.add(new Game("Dobble", "enfant"));
				gameList.add(new Game("Pictionary", "ambiance"));
				gameList.add(new Game("Boggle", "enfant"));
				gameList.add(new Game("Parchis", "famille"));
				gameList.add(new Game("Backgammon", "expert"));
				gameList.add(new Game("Scrabble", "enfant"));
				gameList.add(new Game("Jeu de l'oie", "enfant"));
				gameList.forEach(game -> gameResource.create(toJson(game)));


			List<String> gameRefList = new ArrayList<>();
			gameRefList.add(gameService.read().get(0).getId());

			List<String> volunteerRefList = new ArrayList<>();
			volunteerRefList.add(volunteerService.read().get(0).getId());

			List<Slot> slotList = new ArrayList<>();
			slotList.add(new Slot(new Date(), new Date(), volunteerRefList));

			List<Zone> zoneList = new ArrayList<>();
			zoneList.add(new Zone("Esplanade-droite", gameRefList, slotList));

			zoneList.forEach(zone -> zoneResource.create(toJson(zone)));


			System.out.println("Settlement finished. ");

			Quarkus.waitForExit();
			return 0;
		}
	}
}
