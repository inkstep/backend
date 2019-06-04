package model;

import email.JavaEmail;
import java.util.ArrayList;
import javax.mail.MessagingException;

public class Journey implements Validable {

  public final int userID;
  public final int artistID;

  public final String noRefImages;
  public final String tattooDesc;
  public final String size;
  public final String position;
  public final String availability;
  public final String deposit;

  public Journey(int userID, int artistID, String tattooDesc,
    String size, String position, String availability, String deposit, int noRefImages) {
    this.userID = userID;
    this.artistID = artistID;
    this.tattooDesc = tattooDesc;
    this.size = size;
    this.position = position;
    this.availability = availability;
    this.deposit = deposit;
    this.noRefImages = String.valueOf(noRefImages);
  }

  public String humanAvaliability() {
    String[] days = {"Mondays", "Tuesdays", "Wednesdays", "Thursdays",
      "Fridays", "Saturdays", "Sundays"};
    StringBuilder readableAvailability = new StringBuilder();
    for (int i = 0; i < 7; i++) {
      if (availability.charAt(i) == '1') {
        if (!readableAvailability.toString().equals("")) {
          readableAvailability.append(", ");
        }
        readableAvailability.append(days[i]);
      }
    }

    return readableAvailability.toString();
  }

  // TODO(DJRHails): Add proper validation for Journey Payload
  @Override
  public boolean isValid() {
    return true;
  }
}
