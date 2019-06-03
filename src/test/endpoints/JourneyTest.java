package endpoints;

import org.junit.Test;

public class JourneyTest {
  @Test
  public void emailFormattingTest() {
    JourneyEndpoint.newJourney(
      "Jimmy", "james.dalboth@gmail.com",
      "Ricky", "james.dalboth@gmail.com",
      "SCM", "A flapjack", "10cm",
      "Leg", "Mondays", "1");
  }
}
