package handlers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import store.InkstepStore;
import utils.ImageResizer;

public class JourneyImagesRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public JourneyImagesRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  synchronized
  @Override protected Answer processImpl(EmptyPayload userToBe, Map<String, String> urlParams) {
    System.out.println("Attempting to retrieve images for journey_id " + urlParams.get(":id"));

    List<String> imageData = store.getImagesFromJourneyId(Integer.valueOf(urlParams.get(":id")));

    List<BufferedImage> images = new ArrayList<>();

    for (String data : imageData) {
      byte[] decodedBytes = Base64.getDecoder().decode(data);

      ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
      try {
        BufferedImage image = ImageIO.read(bis);
        images.add(image);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    List<String> resizedData = new ArrayList<>();

    for (BufferedImage image : images) {
      BufferedImage after = ImageResizer.resize(image, 200, 200);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      try {
        ImageIO.write(after, "png", bos);
      } catch (IOException e) {
        e.printStackTrace();
      }

      resizedData.add(
        new String(Base64.getEncoder().encode(bos.toByteArray()),
        StandardCharsets.UTF_8));
    }

    return Answer.ok(dataToJson(resizedData));
  }
}
