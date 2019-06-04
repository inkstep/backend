package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.PreparedStatement;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import model.Passphrase;

public class PassphraseTest {
  @Test
  public void canGeneratePassphrase() {
    String passphrase;
    try {
      passphrase = Passphrase.generate().toString();
    } catch (IOException e) {
      e.printStackTrace();
      fail("IO Exception occured");
      return;
    }

    assertNotNull(passphrase);
  }

  @Test
  public void canGenerateValidPassphrase() throws IOException {
    String passphrase = null;

    passphrase =  Passphrase.generate().toString();

    int capletters = 0;

    for (int i = 0; i < passphrase.length(); i++) {
      if (Character.isUpperCase(passphrase.charAt(i))) {
        capletters++;
      }
    }

    assertEquals(capletters, Passphrase.PASSPHRASE_WORD_COUNT);
  }
}
