package handlers;

import java.util.Map;

import model.Validable;

public interface RequestHandler<V extends Validable> {
  Answer process(V value, Map<String, String> urlParams, boolean shouldReturnHtml);

}
