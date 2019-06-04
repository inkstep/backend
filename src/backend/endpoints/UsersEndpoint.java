package endpoints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Random;
import java.util.stream.Stream;

import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import spark.Route;
import store.InkstepStore;

public class UsersEndpoint {

  public static final int PASSPHRASE_WORD_COUNT = 4;

  private static final String WORDS_PATH = "src/backend/Words.txt";
  private static final int NUMBER_OF_WORDS = 1948;

  private final InkstepStore store;

  public UsersEndpoint(InkstepStore store) {
    this.store = store;
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

      JSONParser parser = new JSONParser();
      JSONObject requestjson = (JSONObject) parser.parse(request.body());

      String userName = (String) requestjson.get("user_name");
      String userEmail = (String) requestjson.get("user_email");
      String passphrase = generatePassphrase();

      User user = new User(userName, userEmail, passphrase);

      int userId = store.putUser(user);

      JSONObject responsejson = new JSONObject();
      responsejson.put("user_id", userId);
      responsejson.put("user_passphrase", passphrase);
      return responsejson.toJSONString();
    };
  }
}
