package model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JourneyStatus {
  WAITING_FOR_RESPONSE,
  BOOKED_IN,
  POST_TATTOO;

  @JsonValue
  public int toCode() {
    return ordinal();
  }
}
