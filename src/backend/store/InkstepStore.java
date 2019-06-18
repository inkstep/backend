package store;

import java.util.List;

import model.*;

public interface InkstepStore {

  // Users
  User getUserFromID(int userID);

  int putUser(User user);

  void removeUser(int userID);

  void updateEmail(int userID, String email);

  // Journeys
  List<Journey> getJourneysForUserID(int userId);

  int createJourney(Journey journey);

  Journey getJourneyFromId(int id);

  int putJourneyImage(int journeyId, String image);

  boolean hasGotAllImages(int journeyId);

  List<String> getImagesFromJourneyId(int journeyId);

  JourneyStage getJourneyStage(int journeyId);

  void updateQuote(int journeyId, int quoteLower, int quoteUpper);

  void offerAppointment(int journeyId, String appointmentString);

  void updateStage(int journeyId, JourneyStage stage);

  void removeJourney(int journeyId);


  // Studios
  Studio getStudioFromID(int studioID);


  // Artists
  List<Artist> getArtists();

  Artist getArtistFromID(int artistId);

  void addArtist(Artist artist);

  List<Studio> getStudios();

    void updateToken(int userId, String newToken);
}
