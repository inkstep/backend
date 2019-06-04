package store;

import java.util.List;

import model.Artist;
import model.Journey;
import model.User;

public interface InkstepStore {
  void addArtist(Artist artist);

  List<Artist> getArtists();

  int putUser(User user);

  int putJourney(Journey journey);

  void putJourneyImages();

  void getJourneysForUsername(String username);
}
