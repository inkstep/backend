package handlers;

import model.Validable;

public class EmptyPayload implements Validable {
  @Override public boolean isValid() {
    return true;
  }
}
