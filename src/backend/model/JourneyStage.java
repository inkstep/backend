package model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JourneyStage {
  WaitingQuote,
  QuoteReceived,
  WaitingAppointmentOffer,
  AppointmentOfferReceived,
  AppointmentBooked,
  ImmediateAftercare,
  WeekOfAftercare,
  MonthOfAftercare,
  Healed;

  @JsonValue
  public int toCode() {
    return ordinal();
  }
}
