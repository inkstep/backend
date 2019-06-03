package store;

import java.util.List;

import database.DatabaseConnection;
import model.Artist;

public class ArtistsDatabaseStore implements ArtistsStore {

  private final DatabaseConnection connection;

  public ArtistsDatabaseStore(DatabaseConnection connection) {
    this.connection = connection;
  }

  @Override public void addArtist(Artist artist) {
    connection.open();

    // TODO: Write artist to db here

    connection.close();
  }

  @Override public List<Artist> getArtist() {
    connection.open();

    // TODO: Get artists from db here

    connection.close();
    return null;
  }
}
