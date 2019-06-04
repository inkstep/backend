package store;

import java.util.List;

import model.Artist;

public interface InkstepStore {
  void addArtist(Artist artist);

  List<Artist> getArtists();

  int putJourney(String noRefImages);
  void putJourneyImages();
}
