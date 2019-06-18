package handlers;

import spark.Response;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Objects;

public abstract class Answer {

  public static final int BAD_USER = 400;
  public static final int SUCCESS = 200;

  public static Answer code(int code) {
    return new StringAnswer(code, "");
  }

  public static Answer ok(String body) {
    return new StringAnswer(SUCCESS, body);
  }

  public static Answer png(BufferedImage img) {
    return new ImageAnswer("image/png", img);
  }

  public static Answer userError(String explanation) {
    return new StringAnswer(BAD_USER, explanation);
  }

  public abstract void update(Response response);
}

