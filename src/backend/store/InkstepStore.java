package store;

import java.util.List;

import model.Artist;
import model.Journey;
import model.Studio;
import model.User;

public interface InkstepStore {

  // Users
  User getUserFromID(int userID);

  int putUser(User user);


  // Journeys
  void getJourneysForUser(User user);

  int putJourney(Journey journey);

  void putJourneyImages();


  // Studios
  Studio getStudioFromID(int studioID);


  // Artists
  List<Artist> getArtists();

  Artist getArtistFromID(int artistId);

  void addArtist(Artist artist);
}
