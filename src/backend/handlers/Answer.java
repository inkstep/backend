package handlers;

import spark.Response;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Objects;

public abstract class Answer {

  public static Answer code(int code) {
    return new StringAnswer(code, "");
  }

  public static Answer ok(String body) {
    return new StringAnswer(200, body);
  }

  public static Answer png(BufferedImage img) {
    return new ImageAnswer("image/png", img);
  }

  public abstract void update(Response response);
}

