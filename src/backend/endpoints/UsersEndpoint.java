package endpoints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Random;
import java.util.stream.Stream;

import database.DatabaseConnection;
import org.json.simple.JSONObject;
import spark.Route;

public class UsersEndpoint {

  public static final int PASSPHRASE_WORD_COUNT = 4;

  private static final String WORDS_PATH = "src/backend/Words.txt";
  private static final int NUMBER_OF_WORDS = 1948;

  private final DatabaseConnection connection;

  public UsersEndpoint(DatabaseConnection connection) {
    this.connection = connection;
  }

  public Object putUser(String userName) throws SQLException {
    String passphrase = null;

    /* Attempt to generate a passphrase */
    try {
      passphrase = generatePassphrase();
    } catch (IOException e) {
      e.printStackTrace();

      return "Error generating user a passphrase";
    }

    /* Create a connection to the database and make an sql insert */
    //    PreparedStatement pstmt = databaseConnection
    //      .prepareStatement("insert into users (user_name, user_passphrase) values (?, ?)");

    //    pstmt.setString(1, userName);
    //    pstmt.setString(2, passphrase);

    //    pstmt.executeUpdate();

    //    databaseConnection.close();

    return "User: " + userName + ", Passphrase: " + passphrase;
  }

  /* Generate a passphrase out of 4 random words from a list */
  public static String generatePassphrase() throws IOException {
    Random random = new Random();

    StringBuilder passphraseBuilder = new StringBuilder();

    for (int i = 0; i < PASSPHRASE_WORD_COUNT; i++) {
      int index = random.nextInt(NUMBER_OF_WORDS);

      Stream<String> lines = Files.lines(Paths.get(WORDS_PATH));
      String passcodePart = lines.skip(index).findFirst().get();

      passcodePart = passcodePart.substring(0, 1).toUpperCase() + passcodePart.substring(1);
      passphraseBuilder.append(passcodePart);
    }

    return passphraseBuilder.toString();
  }

  public Route putUserRoute() {
    return (request, response) -> {
      System.out.println("Request received PUT user");
      String userName = request.queryParams("name");
      if (userName == null) {
        return "name query not supplied";
      }

      String passphrase = "JimmyHarryDannyMatty";

      JSONObject userResponse = new JSONObject();

      userResponse.put("name", userName);
      userResponse.put("passphrase", passphrase);

      return userResponse.toJSONString();
    };
  }
}
