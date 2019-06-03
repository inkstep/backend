package store;

import java.util.List;

import model.Artist;

public interface ArtistsStore {

  void addArtist(Artist artist);

  List<Artist> getArtists();
}
