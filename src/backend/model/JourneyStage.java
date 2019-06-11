package model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JourneyStage {
  WaitingQuote,
  QuoteReceived,
  WaitingAppointmentOffer,
  AppointmentOfferReceived,
  PostTattoo;

  @JsonValue
  public int toCode() {
    return ordinal();
  }
}
