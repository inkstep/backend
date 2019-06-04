package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class Passphrase {
  public static final int PASSPHRASE_WORD_COUNT = 4;

  private static final String WORDS_PATH = "src/backend/Words.txt";
  private static final int NUMBER_OF_WORDS = 1948;

  private final String phrase;

  private Passphrase(String phrase) {
    this.phrase = phrase;
  }

  @Override
  public String toString() {
    return phrase;
  }

  /* Generate a passphrase out of 4 random words from a list */
  public static Passphrase generate() throws IOException {
    Random random = new Random();

    StringBuilder passphraseBuilder = new StringBuilder();

    for (int i = 0; i < PASSPHRASE_WORD_COUNT; i++) {
      int index = random.nextInt(NUMBER_OF_WORDS);

      Stream<String> lines = Files.lines(Paths.get(WORDS_PATH));
      String passcodePart = lines.skip(index).findFirst().get();

      passcodePart = passcodePart.substring(0, 1).toUpperCase() + passcodePart.substring(1);
      passphraseBuilder.append(passcodePart);
    }

    return new Passphrase(passphraseBuilder.toString());
  }
}