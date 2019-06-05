package store;

import static store.InkstepDatabaseSchema.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.healthmarketscience.sqlbuilder.InsertQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import model.Artist;
import model.Journey;
import model.Studio;
import model.User;

public class InkstepDatabaseStore implements InkstepStore {

  private static final String DB_URL =
    "jdbc:mysql://inkstepdb.cqjzj0pfmjrn.eu-west-2.rds.amazonaws.com:3306/inkstep?useSSL=false";
  private static final String DB_USERNAME = "docg1827107group";
  private static final String DB_PASSWORD = System.getenv("INKSTEP_AWS_DB_PW");

  private Connection connection;
  private boolean connected;

  private void open() throws ClassNotFoundException, SQLException {
    if (!connected) {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      connected = true;
    }
  }

  private void close() throws SQLException {
    if (!connected) {
      connection.close();
      connected = false;
    }
  }

  // TODO(mm5917): inline
  private List<List<String>> query(String table, List<String> columns, String whereClause) {
    if (!connected) {
      System.out.println("Not connected!");
      return new ArrayList<>();
    }
    try {
      List<List<String>> returnValues = new ArrayList<>();

      StringBuilder fields = new StringBuilder();

      for (String field : columns) {
        fields.append(field).append(",");
      }

      fields = new StringBuilder(fields.substring(0, fields.length() - 1));

      PreparedStatement pstmt = connection
        .prepareStatement("SELECT " + fields + " FROM " + table + " WHERE " + whereClause);

      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        List<String> dataFields = new ArrayList<>();

        for (String field : columns) {
          dataFields.add(rs.getString(field));
        }
        returnValues.add(dataFields);
      }

      return returnValues;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  @Override public void addArtist(Artist artist) {

  }

  @Override public List<Artist> getArtists() {
    List<Artist> artists = new ArrayList<>();
    try {
      open();

      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM artists");
      while (rs.next()) {
        System.out.println(rs.toString());
        int studioID = rs.getInt(2);
        String name = rs.getString(3);
        String email = rs.getString(4);
        artists.add(new Artist(name, email, studioID));
      }

      close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return artists;
  }

  private String getPreparedInsertQuery(DbTable table, DbColumn[] columns) {
    InsertQuery insertQuery = new InsertQuery(table);
    for (DbColumn column : columns) {
      insertQuery.addPreparedColumns(column);
    }
    return insertQuery.validate().toString();
  }

  @Override public int putUser(User user) {
    int returnId = -1;
    try {
      open();

      // Build prepared statement TODO(mm5917): remove ID column
      DbColumn[] insertInto = {USER_NAME, USER_EMAIL, USER_PHONE, USER_PASSPHRASE};
      String query = getPreparedInsertQuery(USERS, insertInto);
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      // Insert values into statement
      preparedStatement.setString(1, user.name);
      preparedStatement.setString(2, user.email);
      preparedStatement.setString(3, ""); // TODO(mm5917): get phone number
      preparedStatement.setString(4, user.passphrase);

      // Execute the insert statement
      preparedStatement.execute();

      // Get the ID of the last inserted row to return
      preparedStatement = connection.prepareStatement("SELECT LAST_INSERT_ID()");
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        returnId = rs.getInt(1);
      }

      close();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return returnId;
  }

  @Override public void getJourneysForUser(User user) {
  }

  @Override public int createJourney(Journey journey) {
    int returnId = -1;
    try {
      open();

      // Build prepared statement TODO(mm5917): remove ID column
      DbColumn[] insertInto =
        new DbColumn[] {JNY_USER_ID, JNY_ARTIST_ID, JNY_DESCRIPTION, JNY_SIZE, JNY_POSITION,
          JNY_AVAIL, JNY_DEPOSIT, JNY_NO_REF_IMAGES};
      String query = getPreparedInsertQuery(JOURNEYS, insertInto);
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      // Insert values into statement
      preparedStatement.setInt(1, journey.userID);
      preparedStatement.setInt(2, journey.artistID);
      preparedStatement.setString(3, journey.tattooDesc);
      preparedStatement.setString(4, journey.size);
      preparedStatement.setString(5, journey.position);
      preparedStatement.setString(6, journey.availability);
      preparedStatement.setString(7, journey.deposit);
      preparedStatement.setString(8, journey.noRefImages);

      // Execute the insert statement
      preparedStatement.execute();

      // Get the ID of the last inserted row to return
      preparedStatement = connection.prepareStatement("SELECT LAST_INSERT_ID()");
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        returnId = rs.getInt(1);
      }

      close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    return returnId;
  }

  @Override public void putJourneyImages() {
  }

  @Override public Artist getArtistFromID(int artistId) {
    try {
      open();

      List<String> columns = new ArrayList<>();
      columns.add("StudioID");
      columns.add("Name");
      columns.add("Email");
      List<List<String>> results = query("artists", columns, "ID = " + artistId);

      System.out.println(results);

      List<String> row1 = results.get(0);

      int studioId = Integer.parseInt(row1.get(0));
      String name = row1.get(1);
      String email = row1.get(2);

      close();

      return new Artist(name, email, studioId, artistId);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public User getUserFromID(int userID) {
    try {
      open();

      List<String> columns = new ArrayList<>();
      columns.add("Name");
      columns.add("Email");
      columns.add("Passphrase");
      List<List<String>> results = query("users", columns, "ID = " + userID);
      if (results.size() == 0) {
        return null;
      }

      List<String> row1 = results.get(0);

      String name = row1.get(0);
      String email = row1.get(1);
      String passphrase = row1.get(2);

      close();

      return new User(name, email, passphrase, userID);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Studio getStudioFromID(int studioID) {
    try {
      open();

      List<String> columns = new ArrayList<>();
      columns.add("Name");
      List<List<String>> results = query("studios", columns, "ID = " + studioID);
      close();

      List<String> row1 = results.get(0);

      String name = row1.get(0);

      return new Studio(name);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
