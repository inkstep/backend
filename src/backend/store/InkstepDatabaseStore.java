package store;

import static model.JourneyBuilder.aJourney;
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

  private List<List<String>> selectQuery(DbColumn[] columns, Condition[] whereClauses) {
    if (!connected) {
      return new ArrayList<>();
    }
    try {
      SelectQuery selectQuery =
        new SelectQuery().addColumns(columns);

      for (Condition whereClause: whereClauses) {
        selectQuery.addCondition(whereClause);
      }

      PreparedStatement pstmt = connection.prepareStatement(selectQuery.validate().toString());

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

  private List<List<String>> selectQuery(DbColumn[] columns, Condition whereClause) {
    Condition[] clauses =
      new Condition[] {whereClause};
    return selectQuery(columns, clauses);
  }

  private void deleteQuery(DbTable table, Condition whereClause) {
    if (connected) {
      try {
        String query = new DeleteQuery(table).addCondition(whereClause).validate().toString();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.execute();

      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private int getIntFromResult(String result) {
    if (result == null) {
      return -1;
    }

    return Integer.parseInt(result);
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
        String profileImg = rs.getString(5);
        artists.add(new Artist(name, email, profileImg, studioID, artistID));
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

      DbColumn[] columns =
        new DbColumn[] {ARTIST_STUDIO_ID, ARTIST_NAME, ARTIST_EMAIL, ARTIST_PROFILE};
      Condition condition = BinaryCondition.equalTo(ARTIST_ID, artistId);
      List<List<String>> results = selectQuery(columns, condition);

      if (results.size() != 0) {
        List<String> row1 = results.get(0);
        int studioId = Integer.parseInt(row1.get(0));
        String name = row1.get(1);
        String email = row1.get(2);
        String profileImg = row1.get(3);

        artist = new Artist(name, email, profileImg, studioId, artistId);
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

  @Override
  public void updateToken(int userId, String newToken) {
    try {
      open();

      Condition condition = BinaryCondition.equalTo(USER_ID, userId);

      String query = getPreparedUpdateQuery(USERS, USER_DEVICE_TOKEN, newToken, condition);

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.execute();

      close();

    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }
  }

  @Override public Studio getStudioFromID(int studioID) {
    try {
      open();

      DbColumn[] columns = new DbColumn[] {STUDIO_NAME};
      Condition condition = BinaryCondition.equalTo(STUDIO_ID, studioID);
      List<List<String>> results = selectQuery(columns, condition);



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

  @Override public int putUser(User user) {
    try {
      open();

      // Build prepared statement TODO(mm5917): remove ID column
      DbColumn[] insertInto = {USER_NAME, USER_EMAIL, USER_PHONE, USER_DEVICE_TOKEN};
      String query = getPreparedInsertQuery(USERS, insertInto);
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      // Insert values into statement
      preparedStatement.setString(1, user.name);
      preparedStatement.setString(2, user.email);
      preparedStatement.setString(3, ""); // TODO(mm5917): get phone number
      preparedStatement.setString(4, user.token);

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

  @Override public void removeUser(int userID) {
    try {
      open();

      Condition condition = BinaryCondition.equalTo(USER_ID, userID);
      deleteQuery(USERS, condition);

      close();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateEmail(int userID, String email) {
    try {
      open();

      DbColumn column = USER_EMAIL;
      Condition condition = BinaryCondition.equalTo(USER_ID, userID);

      String query = getPreparedUpdateQuery(USERS, column, email, condition);
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.execute();

      close();
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }
  }

  @Override public User getUserFromID(int userID) {
    User user = null;
    try {
      open();

      DbColumn[] columns = new DbColumn[] {USER_NAME, USER_EMAIL, USER_DEVICE_TOKEN};
      Condition condition = BinaryCondition.equalTo(USER_ID, userID);
      List<List<String>> results = selectQuery(columns, condition);

      if (results.size() != 0) {
        List<String> row1 = results.get(0);
        String name = row1.get(0);
        String email = row1.get(1);
        String token = row1.get(2);

        user = new User(name, email, userID, token);
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
          JNY_AVAIL, JNY_NO_REF_IMAGES, JNY_STAGE};
      String query = getPreparedInsertQuery(JOURNEYS, insertInto);
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      // Insert values into statement
      preparedStatement.setInt(1, journey.userID);
      preparedStatement.setInt(2, journey.artistID);
      preparedStatement.setString(3, journey.tattooDesc);
      preparedStatement.setString(4, journey.size);
      preparedStatement.setString(5, journey.position);
      preparedStatement.setString(6, journey.availability);
      preparedStatement.setString(7, journey.noRefImages);
      preparedStatement.setInt(8, journey.stage.toCode());

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


      DbColumn[] columns = new DbColumn[] {JNY_IMAGE_ID};
      Condition condition = BinaryCondition.equalTo(JNY_IMAGE_JNY_ID,
        journeyId);
      List<List<String>> results = selectQuery(columns, condition);

      close();

      return results.size() - 1;
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
      List<List<String>> results = selectQuery(columns, condition);

      if (results.isEmpty()) {
        close();
        return false;
      }

      List<String> row = results.get(0);
      final int noRefImgs = Integer.parseInt(row.get(0));

      columns = new DbColumn[] {JNY_IMAGE_ID};
      condition = BinaryCondition.equalTo(JNY_IMAGE_JNY_ID, journeyId);
      results = selectQuery(columns, condition);

      close();

      final int noUploadedImgs = results.size();

      return noUploadedImgs >= noRefImgs;
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

      System.out.println("About to selectQuery ImageJourneys");

      List<List<String>> results = selectQuery(columns, condition);

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

  @Override public JourneyStage getJourneyStage(int journeyId) {
    try {
      open();

      // Build prepared statement
      DbColumn[] columns = new DbColumn[] {JNY_STAGE};
      Condition condition = BinaryCondition.equalTo(JNY_ID, journeyId);
      List<List<String>> results = selectQuery(columns, condition);

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

  @Override public void updateQuote(int journeyId, int quoteLower,
                                    int quoteUpper) {
    try {
      open();

      DbColumn column = JNY_QUOTE_LOWER;
      Condition condition = BinaryCondition.equalTo(JNY_ID, journeyId);

      String query = getPreparedUpdateQuery(JOURNEYS, column, quoteLower, condition);
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.execute();

      column = JNY_QUOTE_UPPER;

      query = getPreparedUpdateQuery(JOURNEYS, column, quoteUpper, condition);
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

      Condition condition = BinaryCondition.equalTo(JNY_ID, journeyId);

      System.out.println("Appointment String was: " + appointmentString);

      String query =
        getPreparedUpdateQuery(JOURNEYS, JNY_BOOKING_DATE, appointmentString, condition);

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.execute();

      close();
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }
  }

  @Override public void updateStage(int journeyId, JourneyStage stage) {
    try {
      open();

      Condition condition = BinaryCondition.equalTo(JNY_ID, journeyId);

      String query = getPreparedUpdateQuery(JOURNEYS, JNY_STAGE, stage.toCode(), condition);

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.execute();

      close();

    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }
  }

  @Override public Journey removeJourney(int journeyId) {
    try {
      final Journey journey = getJourneyFromId(journeyId);
      open();

      // Build prepared statement to delete journey from table
      Condition condition = BinaryCondition.equalTo(JNY_ID, journeyId);
      deleteQuery(JOURNEYS, condition);

      close();
      return journey;
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public List<Journey> getWaitingListJourneysFromArtistId(int artistId) {
    try {
      open();

      // Build prepared statement
      DbColumn[] columns =
        new DbColumn[] {JNY_ID, JNY_USER_ID, JNY_ARTIST_ID, JNY_DESCRIPTION, JNY_SIZE, JNY_POSITION,
          JNY_AVAIL, JNY_NO_REF_IMAGES, JNY_QUOTE_LOWER, JNY_QUOTE_UPPER, JNY_STAGE,
          JNY_BOOKING_DATE, JNY_STYLE};
      Condition[] clauses =
        new Condition[] {BinaryCondition.equalTo(JNY_ARTIST_ID, artistId),
          BinaryCondition.equalTo(JNY_STAGE, JourneyStage.WaitingList.toCode())};
      List<List<String>> results = selectQuery(columns, clauses);

      close();

      List<Journey> journeys = new ArrayList<>();
      for (List<String> row : results) {
        journeys.add(aJourney()
          .withID(getIntFromResult(row.get(0)))
          .withUserID(getIntFromResult(row.get(1)))
          .withArtistID(getIntFromResult(row.get(2)))
          .withTattooDesc(row.get(3))
          .withSize(row.get(4))
          .withPosition(row.get(5))
          .withStyle(row.get(12))
          .withAvailability(row.get(6))
          .withNoRefImages(getIntFromResult(row.get(7)))
          .withQuoteLower(getIntFromResult(row.get(8)))
          .withQuoteUpper(getIntFromResult(row.get(9)))
          .withStage(getIntFromResult(row.get(10)))
          .withBookingDate(row.get(11))
          .build());
      }

      return journeys;
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  public List<Journey> getJourneysWithOfferedSlot(int artistID, String bookingDate) {
    try {
      open();

      // Build prepared statement
      DbColumn[] columns =
        new DbColumn[] {JNY_ID, JNY_USER_ID, JNY_ARTIST_ID, JNY_DESCRIPTION, JNY_SIZE, JNY_POSITION,
          JNY_AVAIL, JNY_NO_REF_IMAGES, JNY_QUOTE_LOWER, JNY_QUOTE_UPPER, JNY_STAGE,
          JNY_BOOKING_DATE, JNY_STYLE};
      Condition[] clauses =
        new Condition[] {BinaryCondition.equalTo(JNY_ARTIST_ID, artistID),
          BinaryCondition.equalTo(JNY_BOOKING_DATE, bookingDate),
          BinaryCondition.equalTo(JNY_STAGE, JourneyStage.AppointmentOfferReceived.toCode())
        };
      List<List<String>> results = selectQuery(columns, clauses);

      close();

      List<Journey> journeys = new ArrayList<>();
      for (List<String> row : results) {
        journeys.add(aJourney()
          .withID(getIntFromResult(row.get(0)))
          .withUserID(getIntFromResult(row.get(1)))
          .withArtistID(getIntFromResult(row.get(2)))
          .withTattooDesc(row.get(3))
          .withSize(row.get(4))
          .withPosition(row.get(5))
          .withStyle(row.get(12))
          .withAvailability(row.get(6))
          .withNoRefImages(getIntFromResult(row.get(7)))
          .withQuoteLower(getIntFromResult(row.get(8)))
          .withQuoteUpper(getIntFromResult(row.get(9)))
          .withStage(getIntFromResult(row.get(10)))
          .withBookingDate(row.get(11))
          .build());
      }

      return journeys;
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  @Override public Journey getJourneyFromId(int id) {
    try {
      open();

      // Build prepared statement
      DbColumn[] columns =
        new DbColumn[] {JNY_USER_ID, JNY_ARTIST_ID, JNY_DESCRIPTION, JNY_SIZE, JNY_POSITION,
          JNY_AVAIL, JNY_NO_REF_IMAGES, JNY_QUOTE_LOWER, JNY_QUOTE_UPPER, JNY_STAGE,
          JNY_BOOKING_DATE, JNY_STYLE};
      Condition condition = BinaryCondition.equalTo(JNY_ID, id);
      List<List<String>> results = selectQuery(columns, condition);

      close();

      if (results.size() != 1) {
        return null;
      }

      List<String> row = results.get(0);

      return aJourney()
        .withID(id)
        .withUserID(getIntFromResult(row.get(0)))
        .withArtistID(getIntFromResult(row.get(1)))
        .withTattooDesc(row.get(2))
        .withSize(row.get(3))
        .withPosition(row.get(4))
        .withStyle(row.get(11))
        .withAvailability(row.get(5))
        .withNoRefImages(getIntFromResult(row.get(6)))
        .withQuoteLower(getIntFromResult(row.get(7)))
        .withQuoteUpper(getIntFromResult(row.get(8)))
        .withStage(getIntFromResult(row.get(9)))
        .withBookingDate(row.get(10))
        .build();

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
          JNY_AVAIL, JNY_NO_REF_IMAGES, JNY_QUOTE_LOWER, JNY_QUOTE_UPPER, JNY_STAGE,
          JNY_BOOKING_DATE, JNY_STYLE};
      Condition condition = BinaryCondition.equalTo(JNY_USER_ID, userId);
      List<List<String>> results = selectQuery(columns, condition);

      close();

      List<Journey> journeys = new ArrayList<>();
      for (List<String> row : results) {
        journeys.add(aJourney()
          .withID(getIntFromResult(row.get(0)))
          .withUserID(getIntFromResult(row.get(1)))
          .withArtistID(getIntFromResult(row.get(2)))
          .withTattooDesc(row.get(3))
          .withSize(row.get(4))
          .withPosition(row.get(5))
          .withStyle(row.get(12))
          .withAvailability(row.get(6))
          .withNoRefImages(getIntFromResult(row.get(7)))
          .withQuoteLower(getIntFromResult(row.get(8)))
          .withQuoteUpper(getIntFromResult(row.get(9)))
          .withStage(getIntFromResult(row.get(10)))
          .withBookingDate(row.get(11))
          .build());
      }

      return journeys;
    } catch (ClassNotFoundException | SQLException e) {
      close();
      e.printStackTrace();
    }

    return new ArrayList<>();
  }
}
