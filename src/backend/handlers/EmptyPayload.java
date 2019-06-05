package handlers;

import model.Validatable;

public class EmptyPayload implements Validatable {
  @Override public boolean isValid() {
    return true;
  }
}
