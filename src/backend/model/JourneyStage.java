package model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JourneyStage {
  WaitingQuote,
  QuoteReceived,
  WaitingAppointmentOffer,
  AppointmentOfferReceived,
  AppointmentBooked,
  Aftercare,
  Healed,
  Finished,
  WaitingList;

  @JsonValue
  public int toCode() {
    return ordinal();
  }
}
