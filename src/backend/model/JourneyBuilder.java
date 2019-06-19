package model;

public class JourneyBuilder {
  private int journeyID;
  private int userID;
  private int artistID;
  private String tattooDesc;
  private String size;
  private String position;
  private String style;
  private String availability;
  private int noRefImages;
  private int quoteLower;
  private int quoteUpper;
  private int stage;
  private String bookingDate;

  private JourneyBuilder() {}

  public static JourneyBuilder aJourney() {
    return new JourneyBuilder();
  }

  public JourneyBuilder withID(int journeyID) {
    this.journeyID = journeyID;
    return this;
  }

  public JourneyBuilder withUserID(int userID) {
    this.userID = userID;
    return this;
  }

  public JourneyBuilder withArtistID(int artistID) {
    this.artistID = artistID;
    return this;
  }

  public JourneyBuilder withTattooDesc(String tattooDesc) {
    this.tattooDesc = tattooDesc;
    return this;
  }

  public JourneyBuilder withSize(String size) {
    this.size = size;
    return this;
  }

  public JourneyBuilder withPosition(String position) {
    this.position = position;
    return this;
  }

  public JourneyBuilder withStyle(String style) {
    this.style = style;
    return this;
  }

  public JourneyBuilder withAvailability(String availability) {
    if (availability == null) {
      this.availability = "0000000";
    } else {
      this.availability = availability;
    }
    return this;
  }

  public JourneyBuilder withNoRefImages(int noRefImages) {
    this.noRefImages = noRefImages;
    return this;
  }

  public JourneyBuilder withQuoteLower(int quoteLower) {
    this.quoteLower = quoteLower;
    return this;
  }

  public JourneyBuilder withQuoteUpper(int quoteUpper) {
    this.quoteUpper = quoteUpper;
    return this;
  }

  public JourneyBuilder withStage(int stage) {
    this.stage = stage;
    return this;
  }

  public JourneyBuilder withBookingDate(String bookingDate) {
    this.bookingDate = bookingDate;
    return this;
  }

  public Journey build() {
    return new Journey(journeyID, userID, artistID, tattooDesc, size, position, style, availability,
      noRefImages, quoteLower, quoteUpper, stage, bookingDate);
  }
}
