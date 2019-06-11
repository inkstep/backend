package store;

import static store.InkstepDatabaseSchema.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.healthmarketscience.sqlbuilder.*;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import model.*;

public class InkstepDatabaseStore implements InkstepStore {

  private static final String DB_URL =
    "jdbc:mysql://inkstepdb.cqjzj0pfmjrn.eu-west-2.rds.amazonaws.com:3306/inkstep?useSSL=false";
  private static final String DB_USERNAME = "docg1827107group";
  private static final String DB_PASSWORD = System.getenv("INKSTEP_AWS_DB_PW");

  private Connection connection;
  private boolean connected = false;

  private void open() throws ClassNotFoundException, SQLException {
    if (!connected) {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      connected = true;
    }
  }

  private void close() {
    if (connected) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      connected = false;
    }
  }

  private String getPreparedInsertQuery(DbTable table, DbColumn[] columns) {
    return new InsertQuery(table).addPreparedColumns(columns).validate().toString();
  }

  private String getPreparedUpdateQuery(DbTable table, DbColumn column, Object value,
    Condition condition) {
    return new UpdateQuery(table).addSetClause(column, value).addCondition(condition).validate()
      .toString();
  }

  private List<List<String>> query(DbColumn[] columns, Condition whereClause) {
    if (!connected) {
      return new ArrayList<>();
    }
    try {
      String query =
        new SelectQuery().addColumns(columns).addCondition(whereClause).validate().toString();
      PreparedStatement pstmt = connection.prepareStatement(query);

      System.out.println(pstmt.toString());
      ResultSet rs = pstmt.executeQuery();

      List<List<String>> returnValues = new ArrayList<>();
      while (rs.next()) {
        List<String> dataFields = new ArrayList<>();

        for (DbColumn field : columns) {
          dataFields.add(rs.getString(field.getColumnNameSQL()));
        }
        returnValues.add(dataFields);
      }

      System.out.println("Results fetched " + returnValues.size());

      return returnValues;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  /* Artist */
  @Override public void addArtist(Artist artist) {
  }

  @Override public List<Artist> getArtists() {
    List<Artist> artists = new ArrayList<>();
    try {
      open();

      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM artists");

      while (rs.next()) {
        int artistID = rs.getInt(1);
        int studioID = rs.getInt(2);
        String name = rs.getString(3);
        String email = rs.getString(4);
        artists.add(new Artist(name, email, studioID, artistID));
      }

      close();
    } catch (Exception e) {
      close();
      e.printStackTrace();
    }

    return artists;
  }

  @Override public Artist getArtistFromID(int artistId) {
    Artist artist = null;
    try {
      open();

      DbColumn[] columns = new DbColumn[] {ARTIST_STUDIO_ID, ARTIST_NAME, ARTIST_EMAIL};
      Condition condition = BinaryCondition.equalTo(ARTIST_ID, artistId);
      List<List<String>> results = query(columns, condition);

      if (results.size() != 0) {
        List<String> row1 = results.get(0);
        int studioId = Integer.parseInt(row1.get(0));
        String name = row1.get(1);
        String email = row1.get(2);

        artist = new Artist(name, email, studioId, artistId);
      }

      close();
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }
    return artist;
  }


  /* Studio */

  @Override public List<Studio> getStudios() {
    List<Studio> studios = new ArrayList<>();
    try {
      open();

      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM studios");

      while (rs.next()) {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        studios.add(new Studio(name, id));
      }

      close();
    } catch (Exception e) {
      close();
      e.printStackTrace();
    }

    return studios;
  }

  @Override public Studio getStudioFromID(int studioID) {
    try {
      open();

      DbColumn[] columns = new DbColumn[] {STUDIO_NAME};
      Condition condition = BinaryCondition.equalTo(STUDIO_ID, studioID);
      List<List<String>> results = query(columns, condition);



      if (results.size() != 0) {
        List<String> row1 = results.get(0);
        String name = row1.get(0);

        close();

        return new Studio(name, studioID);
      }

      close();
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }
    return null;
  }

  /* User */

  @Override public User getUserFromPassphraseEmail(String passphrase, String email) {
    User user = null;
    try {
      open();

      DbColumn[] columns = new DbColumn[] {USER_ID, USER_NAME};
      Condition condition = ComboCondition.and(BinaryCondition.equalTo(USER_PASSPHRASE, passphrase),
        BinaryCondition.equalTo(USER_EMAIL, email));
      List<List<String>> results = query(columns, condition);

      if (results.size() != 0) {
        List<String> row1 = results.get(0);
        int id = Integer.parseInt(row1.get(0));
        String name = row1.get(1);

        user = new User(name, email, passphrase, id);
      }

      close();
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }
    return user;
  }

  @Override public int putUser(User user) {
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

      int returnId = -1;
      if (rs.next()) {
        returnId = rs.getInt(1);
      }

      close();

      return returnId;
    } catch (SQLException | ClassNotFoundException e) {
      close();
      e.printStackTrace();
    }

    System.out.println("Database insert for user failed with returnId - 1");

    return -1;
  }

  @Override public User getUserFromID(int userID) {
    User user = null;
    try {
      open();

      DbColumn[] columns = new DbColumn[] {USER_NAME, USER_EMAIL, USER_PASSPHRASE};
      Condition condition = BinaryCondition.equalTo(USER_ID, userID);
      List<List<String>> results = query(columns, condition);

      if (results.size() != 0) {
        List<String> row1 = results.get(0);
        String name = row1.get(0);
        String email = row1.get(1);
        String passphrase = row1.get(2);

        user = new User(name, email, passphrase, userID);
      }

      close();
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }

    return user;
  }

  /* Journey */

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
      close();
      e.printStackTrace();
    }

    return returnId;
  }

  @Override public int putJourneyImage(int journeyId, String image) {
    int returnId = -1;
    try {
      open();

      // Build prepared statement TODO(mm5917): remove ID column
      DbColumn[] insertInto = new DbColumn[] {JNY_IMAGE_JNY_ID, JNY_IMAGE_DATA};
      String query = getPreparedInsertQuery(JOURNEY_IMAGES, insertInto);

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      // Insert values into statement
      preparedStatement.setInt(1, journeyId);
      preparedStatement.setString(2, image);

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
      close();
      e.printStackTrace();
    }

    return returnId;
  }

  @Override public boolean hasGotAllImages(int journeyId) {
    try {
      open();

      DbColumn[] columns = new DbColumn[] {JNY_NO_REF_IMAGES};
      Condition condition = BinaryCondition.equalTo(JNY_ID, journeyId);
      List<List<String>> results = query(columns, condition);

      if (results.isEmpty()) {
        close();
        return false;
      }

      List<String> row = results.get(0);
      final int noRefImgs = Integer.parseInt(row.get(0));

      columns = new DbColumn[] {JNY_IMAGE_ID};
      condition = BinaryCondition.equalTo(JNY_IMAGE_JNY_ID, journeyId);
      results = query(columns, condition);

      close();

      final int noUploadedImgs = results.size();

      return noUploadedImgs == noRefImgs;
    } catch (ClassNotFoundException | SQLException e) {
      close();
    }

    return false;
  }

  @Override public List<String> getImagesFromJourneyId(int journeyId) {
    try {
      open();

      DbColumn[] columns = new DbColumn[] {JNY_IMAGE_DATA};
      Condition condition = BinaryCondition.equalTo(JNY_IMAGE_JNY_ID, journeyId);

      System.out.println("About to query ImageJourneys");

      List<List<String>> results = query(columns, condition);

      close();

      System.out.println("Results fetched " + results.size());

      List<String> encodedData = new ArrayList<>();

      for (List<String> row : results) {
        encodedData.add(row.get(0));
      }

      return encodedData;
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  @Override
  public JourneyStage getJourneyStage(int journeyId) {
    try {
      open();

      // Build prepared statement
      DbColumn[] columns =
          new DbColumn[]{JNY_STAGE};
      Condition condition = BinaryCondition.equalTo(JNY_ID, journeyId);
      List<List<String>> results = query(columns, condition);

      close();

      if (results.size() != 1) {
        return null;
      }

      List<String> row = results.get(0);

      return JourneyStage.values()[Integer.parseInt(row.get(0))];

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      close();
    }

    return null;
  }

  @Override public void updateQuote(int journeyId, String quoteLower, String quoteUpper) {
    try {
      open();

      DbColumn column = JNY_QUOTE_LOWER;
      Condition condition = BinaryCondition.equalTo(JNY_ID, journeyId);

      String query = getPreparedUpdateQuery(JOURNEYS, column, quoteLower, condition);
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.execute();

      column = JNY_QUOTE_UPPER;

      query = getPreparedUpdateQuery(JOURNEYS, column, quoteLower, condition);
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.execute();

      close();
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }
  }

  @Override public void offerAppointment(int journeyId, String appointmentString) {
    try {
      open();

      DbColumn column = JNY_OFFERED_APPOINTMENT;
      Condition condition = BinaryCondition.equalTo(JNY_ID, journeyId);

      appointmentString += ":00";

      String query = getPreparedUpdateQuery(JOURNEYS, column, appointmentString, condition);

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.execute();

      close();
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }
  }

  @Override
  public void updateStage(int journeyId, JourneyStage stage) {
    try {
      open();

      DbColumn column = JNY_STAGE;
      Condition condition = BinaryCondition.equalTo(JNY_ID, journeyId);

      String query = getPreparedUpdateQuery(JOURNEYS, column, stage.toCode(), condition);

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.execute();

      close();
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }
  }

  @Override public Journey getJourneyFromId(int id) {
    try {
      open();

      // Build prepared statement
      DbColumn[] columns =
        new DbColumn[] {JNY_USER_ID, JNY_ARTIST_ID, JNY_DESCRIPTION, JNY_SIZE, JNY_POSITION,
          JNY_AVAIL, JNY_DEPOSIT, JNY_NO_REF_IMAGES, JNY_QUOTE_LOWER, JNY_QUOTE_UPPER,
            JNY_STAGE};
      Condition condition = BinaryCondition.equalTo(JNY_ID, id);
      List<List<String>> results = query(columns, condition);

      close();

      if (results.size() != 1) {
        return null;
      }

      List<String> row = results.get(0);

      return new Journey(id, getIntFromResult(row.get(0)), getIntFromResult(row.get(1)), row.get(2),
        row.get(3), row.get(4), row.get(5), row.get(6), getIntFromResult(row.get(7)),
        getIntFromResult(row.get(8)), getIntFromResult(row.get(9)), getIntFromResult(row.get(10)));

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      close();
    }

    return null;
  }

  @Override public List<Journey> getJourneysForUserID(int userId) {
    try {
      open();

      // Build prepared statement
      DbColumn[] columns =
        new DbColumn[] {JNY_ID, JNY_USER_ID, JNY_ARTIST_ID, JNY_DESCRIPTION, JNY_SIZE, JNY_POSITION,
          JNY_AVAIL, JNY_DEPOSIT, JNY_NO_REF_IMAGES, JNY_QUOTE_LOWER, JNY_QUOTE_UPPER,
            JNY_STAGE};
      Condition condition = BinaryCondition.equalTo(JNY_USER_ID, userId);
      List<List<String>> results = query(columns, condition);

      close();

      List<Journey> journeys = new ArrayList<>();
      for (List<String> row : results) {
        journeys.add(new Journey(getIntFromResult(row.get(0)), getIntFromResult(row.get(1)),
          getIntFromResult(row.get(2)), row.get(3), row.get(4), row.get(5), row.get(6), row.get(7),
          getIntFromResult(row.get(8)), getIntFromResult(row.get(9)), getIntFromResult(row.get(10)),
          getIntFromResult(row.get(11))));
      }

      return journeys;
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  public int getIntFromResult(String result) {
    if (result == null) {
      return -1;
    }

    return Integer.parseInt(result);
  }
}
