package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageResizer {

  public static BufferedImage resize(BufferedImage inputImage, int scaledWidth, int scaledHeight) {

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

  public static BufferedImage resize(BufferedImage inputImage, double percent) {
    int scaledWidth = (int) (inputImage.getWidth() * percent);
    int scaledHeight = (int) (inputImage.getHeight() * percent);
    return resize(inputImage, scaledWidth, scaledHeight);
  }

}