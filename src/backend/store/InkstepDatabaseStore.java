package store;

import static store.InkstepDatabaseSchema.ARTIST_EMAIL;
import static store.InkstepDatabaseSchema.ARTIST_ID;
import static store.InkstepDatabaseSchema.ARTIST_NAME;
import static store.InkstepDatabaseSchema.ARTIST_STUDIO_ID;
import static store.InkstepDatabaseSchema.JNY_ARTIST_ID;
import static store.InkstepDatabaseSchema.JNY_AVAIL;
import static store.InkstepDatabaseSchema.JNY_DEPOSIT;
import static store.InkstepDatabaseSchema.JNY_DESCRIPTION;
import static store.InkstepDatabaseSchema.JNY_ID;
import static store.InkstepDatabaseSchema.JNY_IMAGE_DATA;
import static store.InkstepDatabaseSchema.JNY_IMAGE_ID;
import static store.InkstepDatabaseSchema.JNY_IMAGE_JNY_ID;
import static store.InkstepDatabaseSchema.JNY_NO_REF_IMAGES;
import static store.InkstepDatabaseSchema.JNY_POSITION;
import static store.InkstepDatabaseSchema.JNY_SIZE;
import static store.InkstepDatabaseSchema.JNY_USER_ID;
import static store.InkstepDatabaseSchema.JOURNEYS;
import static store.InkstepDatabaseSchema.JOURNEY_IMAGES;
import static store.InkstepDatabaseSchema.STUDIO_ID;
import static store.InkstepDatabaseSchema.STUDIO_NAME;
import static store.InkstepDatabaseSchema.USERS;
import static store.InkstepDatabaseSchema.USER_EMAIL;
import static store.InkstepDatabaseSchema.USER_ID;
import static store.InkstepDatabaseSchema.USER_NAME;
import static store.InkstepDatabaseSchema.USER_PASSPHRASE;
import static store.InkstepDatabaseSchema.USER_PHONE;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.InsertQuery;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Artist;
import model.Journey;
import model.Studio;
import model.User;
import org.apache.commons.io.FileUtils;

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

  private String getPreparedInsertQuery(DbTable table, DbColumn[] columns) {
    return new InsertQuery(table).addPreparedColumns(columns).validate().toString();
  }

  private String getPreparedSelectQuery(DbColumn[] columns, Condition condition) {
    return new SelectQuery().addColumns(columns).addCondition(condition).validate().toString();
  }

  private List<List<String>> query(DbColumn[] columns, Condition whereClause) {
    if (!connected) {
      return new ArrayList<>();
    }
    try {
      PreparedStatement pstmt =
        connection.prepareStatement(getPreparedSelectQuery(columns, whereClause));

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

  @Override
  public void addArtist(Artist artist) {
  }

  @Override
  public List<Artist> getArtists() {
    List<Artist> artists = new ArrayList<>();
    try {
      open();

      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM artists");
      while (rs.next()) {
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

  @Override
  public int putUser(User user) {
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
      e.printStackTrace();
    }

    System.out.println("Database insert for user failed with returnId - 1");

    return -1;
  }

  @Override
  public int createJourney(Journey journey) {
    int returnId = -1;
    try {
      open();

      // Build prepared statement TODO(mm5917): remove ID column
      DbColumn[] insertInto =
        new DbColumn[]{JNY_USER_ID, JNY_ARTIST_ID, JNY_DESCRIPTION, JNY_SIZE, JNY_POSITION,
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

  @Override
  public int putJourneyImage(int journeyId, String image) {
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
      e.printStackTrace();
    }

    return returnId;
  }

  @Override
  public boolean hasGotAllImages(int journeyId) {
    try {
      open();

      DbColumn[] columns = new DbColumn[]{JNY_NO_REF_IMAGES};
      Condition condition = BinaryCondition.equalTo(JNY_ID, journeyId);
      List<List<String>> results = query(columns, condition);

      if (results.isEmpty()) {
        return false;
      }

      List<String> row = results.get(0);
      final int noRefImgs = Integer.parseInt(row.get(0));

      columns = new DbColumn[]{JNY_IMAGE_ID};
      condition = BinaryCondition.equalTo(JNY_IMAGE_JNY_ID, journeyId);
      results = query(columns, condition);

      final int noUploadedImgs = results.size();

      close();

      return noUploadedImgs == noRefImgs;
    } catch (ClassNotFoundException | SQLException e) {
      return false;
    }
  }

  @Override
  public List<File> getImagesFromJourneyId(int journeyId) {
    try {
      open();

      DbColumn[] columns = new DbColumn[]{JNY_IMAGE_DATA};
      Condition condition = BinaryCondition.equalTo(JNY_IMAGE_JNY_ID, journeyId);
      List<List<String>> results = query(columns, condition);

      close();

      if (results.size() != 6) {
        return new ArrayList<>();
      }

      List<File> images = new ArrayList<>();

      int imgCount = 0;
      
      for (List<String> row : results) {
        String encodedImage = row.get(0);

        System.out.println("Decoding file");

        byte[] decodedBytes = Base64.getDecoder().decode(encodedImage);
        File imageFile = new File("email" + imgCount + ".png");
        FileUtils.writeByteArrayToFile(imageFile, decodedBytes);

        System.out.println("File created " + imageFile.getAbsolutePath());

        images.add(imageFile);
      }

      return images;
    } catch (ClassNotFoundException | SQLException | IOException e) {
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  @Override
  public Journey getJourneyFromId(int id) {
    try {
      open();

      // Build prepared statement
      DbColumn[] columns =
        new DbColumn[]{JNY_USER_ID, JNY_ARTIST_ID, JNY_DESCRIPTION, JNY_SIZE, JNY_POSITION,
          JNY_AVAIL, JNY_DEPOSIT, JNY_NO_REF_IMAGES};
      Condition condition = BinaryCondition.equalTo(JNY_ID, id);
      List<List<String>> results = query(columns, condition);

      close();

      if (results.size() != 1) {
        return null;
      }

      List<String> row = results.get(0);

      return new Journey(
        Integer.parseInt(row.get(0)),
        Integer.parseInt(row.get(1)),
        row.get(2),
        row.get(3),
        row.get(4),
        row.get(5),
        row.get(6),
        Integer.parseInt(row.get(7))
      );

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      return null;
    }
  }


  @Override
  public List<Journey> getJourneysForUserID(int userId) {
    try {
      open();

      // Build prepared statement
      DbColumn[] columns =
        new DbColumn[]{JNY_USER_ID, JNY_ARTIST_ID, JNY_DESCRIPTION, JNY_SIZE, JNY_POSITION,
          JNY_AVAIL, JNY_DEPOSIT, JNY_NO_REF_IMAGES};
      Condition condition = BinaryCondition.equalTo(JNY_USER_ID, userId);
      List<List<String>> results = query(columns, condition);

      close();

      if (results.size() == 0) {
        return null;
      }

      List<Journey> journeys = new ArrayList<>();

      for (List<String> row : results) {

        journeys.add(new Journey(
          Integer.parseInt(row.get(0)),
          Integer.parseInt(row.get(1)),
          row.get(2),
          row.get(3),
          row.get(4),
          row.get(5),
          row.get(6),
          Integer.parseInt(row.get(7))
        ));
      }

      return journeys;
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Artist getArtistFromID(int artistId) {
    Artist artist = null;
    try {
      open();

      DbColumn[] columns = new DbColumn[]{ARTIST_STUDIO_ID, ARTIST_NAME, ARTIST_EMAIL};
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
      e.printStackTrace();
    }
    return artist;
  }

  @Override
  public User getUserFromID(int userID) {
    User user = null;
    try {
      open();

      DbColumn[] columns = new DbColumn[]{USER_NAME, USER_EMAIL, USER_PASSPHRASE};
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
      e.printStackTrace();
    }
    return user;
  }

  @Override
  public Studio getStudioFromID(int studioID) {
    Studio studio = null;
    try {
      open();

      DbColumn[] columns = new DbColumn[]{STUDIO_NAME};
      Condition condition = BinaryCondition.equalTo(STUDIO_ID, studioID);
      List<List<String>> results = query(columns, condition);

      if (results.size() != 0) {
        List<String> row1 = results.get(0);
        String name = row1.get(0);

        studio = new Studio(name);
      }

      close();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return studio;
  }
}
