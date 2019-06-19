package handlers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import store.InkstepStore;

public class TimeRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public TimeRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override
  protected Answer processImpl(EmptyPayload value, Map<String, String> queryParams) {
    Map<String, String> responseMap = new HashMap<String, String>() {{
      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
      put("image_id", LocalDateTime.now(ZoneId.of("Europe/London")).format(dateFormatter));
    }};

    return Answer.ok(dataToJson(responseMap));
  }
}
