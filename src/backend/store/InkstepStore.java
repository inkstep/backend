package store;

import java.util.List;

import model.Artist;
import model.Journey;
import model.Studio;
import model.User;

public interface InkstepStore {
  void addArtist(Artist artist);

  List<Artist> getArtists();

  int putUser(User user);

  int createJourney(Journey journey);

  void putJourneyImages();

  void getJourneysForUsername(String username);

  Artist getArtistFromId(int artistId);

  User getUserFromId(int userId);

  Studio getStudioFromId(int studioId);
}
