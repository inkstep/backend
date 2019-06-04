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

    System.out.println(DB_PASSWORD);

    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      connected = true;
    } catch (Exception e) {
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

  private void query(String query) {
    if (!connected) {
      return;
    }
    try {
      //connection.prepareStatement(sql)
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        System.out.println(rs.getString(1) + "  " + rs.getString(2));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private int insert(String table, Map<String, String> data) {
    if (!connected) {
      return -1;
    }
    try {
      StringBuilder fields = new StringBuilder(" (");
      StringBuilder values = new StringBuilder(" (");

      for (String field : data.keySet()) {
        fields.append(field).append(",");
        values.append(data.get(field)).append(",");
      }

      fields = new StringBuilder(fields.substring(0, fields.length() - 1));
      values = new StringBuilder(values.substring(0, values.length() - 1));

      fields.append(") ");
      values.append(") ");

      String cmd = "INSERT INTO " + table + fields + "VALUE" + values;

      System.out.println(cmd);

      PreparedStatement pstmt = connection.prepareStatement(cmd);
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
    return new ArrayList<>();
  }

  @Override
  public int putJourney(Journey journey) {
    open();
    Map<String, String> data = new HashMap<>();
    data.put("NoRefImgs", journey.noRefImages);

    int returnId = insert("journeys", data);

    close();

    return returnId;
  }

  @Override
  public void putJourneyImages() {

  }

  @Override
  public void getJourneysForUsername(String username) {
    
  }
}
