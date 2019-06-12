package handlers;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import store.InkstepStore;

public class JourneyImagesRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public JourneyImagesRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

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
      BufferedImage after = new BufferedImage(
        image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
      AffineTransform at = new AffineTransform();
      at.scale(0.2, 0.2);
      AffineTransformOp scaleOp =
        new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
      after = scaleOp.filter(image, after);

      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      try {
        ImageIO.write(after, "png", bos );
      } catch (IOException e) {
        e.printStackTrace();
      }

      try {
        resizedData.add(new String(Base64.getEncoder().encode(bos.toByteArray()), "UTF-8"));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }

    return Answer.ok(dataToJson(resizedData));
  }
}
