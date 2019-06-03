package version.v2;

import org.junit.Test;

public class JourneyTest {

  @Test
  public void emailFormattingTest() {
    Journey.newJourney(
        "Jimmy", "james.dalboth@gmail.com",
        "Ricky", "james.dalboth@gmail.com",
        "SCM", "A flapjack", "10cm",
        "Leg", "Mondays", "1");
  }
}