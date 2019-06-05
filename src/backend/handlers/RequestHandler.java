package handlers;

import java.util.Map;

import model.Validatable;

public interface RequestHandler<V extends Validatable> {
  Answer process(V value, Map<String, String> urlParams);
}
