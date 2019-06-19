package handlers;

import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import store.InkstepStore;
import utils.ImageResizer;

public class ThumbnailRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public ThumbnailRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  synchronized
  @Override protected Answer processImpl(EmptyPayload userToBe, Map<String, String> urlParams) {
    System.out.println("Attempting to retrieve images for journey_id " + urlParams.get(":jid"));

    final int journeyId = Integer.valueOf(urlParams.get(":jid"));
    final int imageId = Integer.valueOf(urlParams.get(":iid"));

    List<String> images = store.getImagesFromJourneyId(journeyId);

    if (imageId >= images.size() || imageId < 0) {
      return Answer.code(Answer.NOT_FOUND);
    }

    // TODO(mm5917): null pointer exception
    String imageData = store.getImagesFromJourneyId(journeyId).get(imageId);

    BufferedImage image;

    byte[] decodedBytes = Base64.getDecoder().decode(imageData);

    ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
    try {
      image = ImageIO.read(bis);
      BufferedImage after = ImageResizer.resize(image, 200, 200);
      return Answer.png(after);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Answer.code(511);
  }
}
