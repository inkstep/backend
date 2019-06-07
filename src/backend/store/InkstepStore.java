package store;

import java.io.File;
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
  List<Journey> getJourneysForUserID(int userId);

  int createJourney(Journey journey);

  Journey getJourneyFromId(int id);

  int putJourneyImage(int journeyId, String image);

  boolean hasGotAllImages(int journeyId);

  List<String> getImagesFromJourneyId(int journeyId);


  // Studios
  Studio getStudioFromID(int studioID);


  // Artists
  List<Artist> getArtists();

  Artist getArtistFromID(int artistId);

  void addArtist(Artist artist);

  List<Studio> getStudios();

  User getUserFromPassphraseEmail(String passphrase, String email);
}
