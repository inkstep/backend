package store;

import java.util.List;

import model.*;

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

  Stage getJourneyStage(int journeyId);

  void updateQuote(int journeyId, String quoteString);

  void offerAppointment(int journeyId, String appointmentString);

  void updateStage(int journeyId, Stage stage);

  // Studios
  Studio getStudioFromID(int studioID);


  // Artists
  List<Artist> getArtists();

  Artist getArtistFromID(int artistId);

  void addArtist(Artist artist);

  List<Studio> getStudios();

  User getUserFromPassphraseEmail(String passphrase, String email);
}
