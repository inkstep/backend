package handlers;

import spark.Response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageAnswer extends Answer {

  public final String contentType;
  public final BufferedImage img;

  public ImageAnswer(String contentType, BufferedImage img) {

    this.contentType = contentType;
    this.img = img;
  }

  @Override
  public void update(Response response) {
    try {
      ImageIO.write(img, "png", response.raw().getOutputStream());
      response.type("image/png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
