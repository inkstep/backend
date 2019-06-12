package handlers;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
      BufferedImage after = ImageResizer.resize(image, 0.5);
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

class ImageResizer {

  /**
   * Resizes an image to a absolute width and height (the image may not be
   * proportional)
   * @param scaledWidth absolute width in pixels
   * @param scaledHeight absolute height in pixels
   */
  private static BufferedImage resize(BufferedImage inputImage, int scaledWidth, int scaledHeight) {

    // creates output image
    BufferedImage outputImage = new BufferedImage(scaledWidth,
      scaledHeight, inputImage.getType());

    // scales the input image to the output image
    Graphics2D g2d = outputImage.createGraphics();
    g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
    g2d.dispose();

    // writes to output file
    return outputImage;
  }

  /**
   * Resizes an image by a percentage of original size (proportional).
   * @param percent a double number specifies percentage of the output image
   * over the input image.
   */
  public static BufferedImage resize(BufferedImage inputImage, double percent) {
    int scaledWidth = (int) (inputImage.getWidth() * percent);
    int scaledHeight = (int) (inputImage.getHeight() * percent);
    return resize(inputImage, scaledWidth, scaledHeight);
  }

}
