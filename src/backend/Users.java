import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.stream.Stream;

public class Users {


  public static final String WORDS_PATH = "src/backend/Words.txt";
  public static final int PASSPHRASE_WORD_LENGTH = 4;
  public static final int NUMBER_OF_WORDS = 1948;

  public static Object putUser(String userName) throws SQLException {
    String passPhrase = null;

    /* Attempt to generate a passphrase */
    try {
      passPhrase = generatePassphrase();
    } catch (IOException e) {
      e.printStackTrace();

      return "Error generating user a passphrase";
    }

    /* Create a connection to the database and make an sql insert */
    Connection c = Database.getConnection();
    PreparedStatement pstmt = c.prepareStatement(
        "insert into users (user_name, user_passphrase) values (?, ?)"
    );

    pstmt.setString(1, userName);
    pstmt.setString(2, passPhrase);

    pstmt.executeUpdate();

    c.close();

    return "User: " + userName + ", Passphrase: " + passPhrase;
  }

  /* Generate a passphrase out of 4 random words from a list */
  public static String generatePassphrase() throws IOException {
    Random random = new Random();

    StringBuilder passphraseBuilder = new StringBuilder();

    for (int i = 0; i < PASSPHRASE_WORD_LENGTH; i++) {
      int index = random.nextInt(]NUMBER_OF_WORDS);

      Stream<String> lines = Files.lines(Paths.get(WORDS_PATH));
      String passcodePart = lines.skip(index).findFirst().get();

      passcodePart = passcodePart.substring(0, 1).toUpperCase() + passcodePart.substring(1);
      passphraseBuilder.append(passcodePart);
    }

    return passphraseBuilder.toString();
  }
}
