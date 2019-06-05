package handlers;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Validatable;
import spark.Request;
import spark.Response;
import spark.Route;
import store.InkstepStore;

public abstract class AbstractRequestHandler<V extends Validatable>
  implements RequestHandler<V>, Route {

  private Class<V> valueClass;
  protected InkstepStore store;

  public static final int BAD_REQUEST = 400;

  public AbstractRequestHandler(Class<V> valueClass, InkstepStore store) {
    this.valueClass = valueClass;
    this.store = store;
  }

  public static String dataToJson(Object data) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      StringWriter sw = new StringWriter();
      mapper.writeValue(sw, data);
      return sw.toString();
    } catch (IOException e) {
      throw new RuntimeException("IOException from a StringWriter?");
    }
  }

  public final Answer process(V value, Map<String, String> queryParams) {
    if (!value.isValid()) {
      return Answer.empty(BAD_REQUEST);
    } else {
      return processImpl(value, queryParams);
    }
  }

  protected abstract Answer processImpl(V value, Map<String, String> queryParams);

  @Override public Object handle(Request request, Response response) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String usableBody = request.body();
    if (usableBody.equals("")) {
      usableBody = "{}";
    }
    V value = objectMapper.readValue(usableBody, valueClass);

    System.out.println(request.params());
    Answer answer = process(value, new HashMap<>(request.params()));
    response.status(answer.getCode());
    response.type("application/json");
    response.body(answer.getBody());
    return answer.getBody();
  }

}
