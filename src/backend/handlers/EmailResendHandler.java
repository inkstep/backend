package handlers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;

import email.JourneyMail;
import spark.Request;
import spark.Response;
import spark.Route;
import store.InkstepStore;

public class EmailResendHandler implements Route {

  private InkstepStore store;

  public EmailResendHandler(InkstepStore store) {
    this.store = store;
  }

  @Override
  public Object handle(Request request, Response response) throws Exception {
    int journeyId = Integer.valueOf(request.params(":id"));
    System.out.println("SENDING EMAIL!");

    List<String> imageData = store.getImagesFromJourneyId(journeyId);
    List<File> images = new ArrayList<>();

    int imgCount = 0;

    for (String encodedImage : imageData) {
      System.out.println("Decoding file");

      byte[] decodedBytes = Base64.getDecoder().decode(encodedImage);
      File imageFile = new File("email" + imgCount + ".png");
      try {
        FileUtils.writeByteArrayToFile(imageFile, decodedBytes);
        System.out.println("File created " + imageFile.getAbsolutePath());
      } catch (IOException e) {
        e.printStackTrace();
      }

      images.add(imageFile);

      imgCount++;
    }

    System.out.println("Sending " + images.size() + " pictures as attachments");
    try {
      JourneyMail.sendNewTattooRequestEmail(
        store,
        store.getJourneyFromId(journeyId),
        images
      );
      return Answer.ok("Successfully sent new tattoo request");
    } catch (Exception e) {
      return Answer.userError(e.toString());
    }
  }
}
