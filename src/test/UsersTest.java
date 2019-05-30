import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

public class UsersTest {

  @Test
  public void canGeneratePassphrase() {
    String passphrase = null;
    try {
      passphrase = Users.generatePassphrase();
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertNotNull(passphrase);
  }

  @Test
  public void canGenerateValidPassphrase() throws IOException {
    String passphrase = null;

    passphrase = Users.generatePassphrase();

    int capletters = 0;

    for (int i = 0; i < passphrase.length(); i++) {
      if (Character.isUpperCase(passphrase.charAt(i))) {
        capletters++;
      }
    }

    assertEquals(capletters, Users.PASSPHRASE_WORD_LENGTH);
  }

}