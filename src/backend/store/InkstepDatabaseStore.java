package store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  // Return a connection to the database with the following info
  /*public static synchronized Connection getConnection() {
    Connection c = null;
    try {
      Class.forName("org.postgresql.Driver");
      String host = "db.doc.ic.ac.uk:5432";
      String database = "g1827107_u";
      String username = "g1827107_u";
      String password = System.getenv("database_password");
      String url = "jdbc:postgresql://" + host + "/" + database;
      String driver = "org.postgresql.Driver";
      Class.forName(driver);

      c = DriverManager.getConnection(url, username,
        password);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }

    System.out.println("Opened database successfully");

    return c;
  }*/

  private void open() {
    if (connected) {
      return;
    }

    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      connected = true;
    } catch (Exception e) {
      System.out.println("Failed to open connection");
      e.printStackTrace();
    }
  }

  private void close() {
    if (!connected) {
      return;
    }
    try {
      connection.close();
      connected = false;
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private List<List<String>> query(String table, List<String> columns, String whereClause) {
    if (!connected) {
      System.out.println("Not connected!");
      return new ArrayList<>();
    }
    try {
      List<List<String>> returnValues = new ArrayList<>();

      StringBuilder fields = new StringBuilder();

      for (String field : columns) {
        fields.append("`").append(field).append("`,");
      }

      fields = new StringBuilder(fields.substring(0, fields.length() - 1));

      PreparedStatement pstmt = connection
        .prepareStatement("SELECT " + fields + " FROM " + table + " WHERE ?");

      pstmt.setString(1, whereClause);

      System.out.println(pstmt.toString());
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

  private int insert(String table, Map<String, String> data) {
    if (!connected) {
      return -1;
    }
    try {
      StringBuilder fields = new StringBuilder(" (");
      StringBuilder values = new StringBuilder(" (");

      for (String field: data.keySet()) {
        fields.append("`").append(field).append("`,");
        values.append("?,");
      }

      fields = new StringBuilder(fields.substring(0, fields.length() - 1));
      values = new StringBuilder(values.substring(0, values.length() - 1));

      fields.append(") ");
      values.append(") ");


      String cmd = "INSERT INTO " + table + fields + "VALUES" + values;

      PreparedStatement pstmt = connection.prepareStatement(cmd);

      int index = 1;
      for (String field : data.keySet()) {
        pstmt.setString(index, data.get(field));
        index++;
      }

      pstmt.execute();

      pstmt = connection.prepareStatement("SELECT LAST_INSERT_ID()");
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        return rs.getInt(1);
      }

      return -1;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return -1;
  }

  @Override public void addArtist(Artist artist) {

  }

  @Override public List<Artist> getArtists() {
    open();

    List<Artist> artists = new ArrayList<>();
    try {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM artists");
      while (rs.next()) {
        int studioID = rs.getInt(2);
        String name = rs.getString(3);
        String email = rs.getString(4);
        artists.add(new Artist(name, email, studioID));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    close();
    return artists;
  }

  @Override public int putUser(User user) {
    open();

    Map<String, String> data = new HashMap<>();
    data.put("Name", user.name);
    data.put("Email", user.email);
    data.put("Passphrase", user.passphrase);

    int returnId = insert("users", data);

    close();
    return returnId;
  }

  @Override public void getJourneysForUser(User user) {
  }

  @Override public int createJourney(Journey journey) {
    open();

    Map<String, String> data = new HashMap<>();
    data.put("NoRefImgs", journey.noRefImages);
    data.put("UserID", String.valueOf(journey.userID));
    data.put("ArtistID", String.valueOf(journey.artistID));
    data.put("Description", journey.tattooDesc);
    data.put("Size", journey.size);
    data.put("Position", journey.position);
    data.put("Availability", journey.availability);
    data.put("Deposit", journey.deposit);

    int returnId = insert("journeys", data);

    close();
    return returnId;
  }

  @Override
  public int putJourneyImage(String image) {
    return 1;
  }


  @Override public Artist getArtistFromID(int artistId) {
    open();

    List<String> columns = new ArrayList<>();
    columns.add("StudioID");
    columns.add("Name");
    columns.add("Email");
    List<List<String>> results = query("artists", columns, "ID = " + artistId);

    if (results.size() == 0) {
      close();
      return null;
    }
    System.out.println(results);

    List<String> row1 = results.get(0);

    int studioId = Integer.parseInt(row1.get(0));
    String name = row1.get(1);
    String email = row1.get(2);

    close();

    return new Artist(name, email, studioId, artistId);
  }

  @Override public User getUserFromID(int userID) {
    open();

    List<String> columns = new ArrayList<>();
    columns.add("Name");
    columns.add("Email");
    columns.add("Passphrase");
    List<List<String>> results = query("users", columns, "ID = " + userID);

    if (results.size() == 0) {
      close();
      return null;
    }

    List<String> row1 = results.get(0);

    String name = row1.get(0);
    String email = row1.get(1);
    String passphrase = row1.get(2);

    close();
    return new User(name, email, passphrase, userID);
  }

  @Override public Studio getStudioFromID(int studioID) {
    open();
    List<String> columns = new ArrayList<>();
    columns.add("Name");
    List<List<String>> results = query("studios", columns, "ID = " + studioID);

    if (results.size() == 0) {
      close();
      return null;
    }


    List<String> row1 = results.get(0);

    String name = row1.get(0);

    close();
    return new Studio(name);
  }
}
