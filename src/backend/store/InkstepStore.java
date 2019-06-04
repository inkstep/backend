package store;

import java.util.List;

import model.Artist;
import model.Journey;
import model.Studio;

public interface InkstepStore {
  void addArtist(Artist artist);

  List<Artist> getArtists();

  Studio getStudio(int studioID);

  int putJourney(Journey journey);

  void putJourneyImages();

  void getJourneysForUsername(String username);
}
