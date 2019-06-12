package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Journey implements Validatable {

  public final int journeyID;
  public final int userID;
  public final int artistID;

  public final String noRefImages;
  public final String tattooDesc;
  public final String size;
  public final String position;
  public final String availability;
  public final String deposit;
  public final int quoteLower;
  public final int quoteUpper;
  public final JourneyStage stage;
  public final String bookingDate;

  @JsonCreator
  public Journey(
    @JsonProperty("id") int journeyID,
    @JsonProperty("user_id") int userID,
    @JsonProperty("artist_id") int artistID,
    @JsonProperty("tattoo_desc") String tattooDesc,
    @JsonProperty("size") String size,
    @JsonProperty("position") String position,
    @JsonProperty("availability") String availability,
    @JsonProperty("deposit") String deposit,
    @JsonProperty("ref_images") int noRefImages,
    @JsonProperty("quote_lower") int quoteLower,
    @JsonProperty("quote_upper") int quoteUpper,
    @JsonProperty("stage") int stage,
    @JsonProperty("booking_date") String bookingDate) {
    this.journeyID = journeyID;
    this.userID = userID;
    this.artistID = artistID;
    this.tattooDesc = tattooDesc;
    this.size = size;
    this.position = position;
    this.availability = availability;
    this.deposit = deposit;
    this.noRefImages = String.valueOf(noRefImages);
    this.quoteLower = quoteLower;
    this.quoteUpper = quoteUpper;
    this.stage = JourneyStage.values()[stage];
    this.bookingDate = bookingDate;
  }

  public String humanAvailability() {
    String[] days =
      {"Mondays", "Tuesdays", "Wednesdays", "Thursdays", "Fridays", "Saturdays", "Sundays"};
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
    return userID >= 0 && artistID >= 0 && availability.length() == 7;
  }

  @Override
  public String toString() {
    return "Journey {" +
      " ID='" + journeyID + "'" +
      " userID='" + userID + "'" +
      ", artistID='" + artistID + "'" +
      ", noRefImages='" + noRefImages + "'" +
      ", tattooDesc='" + tattooDesc + "'" +
      ", size='" + size + "'" +
      ", position='" + position + "'" +
      ", availability='" + availability + "'" +
      ", deposit='" + deposit + "'" +
      ", quoteLower='" + quoteLower + "'" +
      ", quoteUpper='" + quoteUpper + "'" +
      ", stage='" + stage + "'" +
      ", bookingDate='" + bookingDate + "'" +
      "}";
  }
}
