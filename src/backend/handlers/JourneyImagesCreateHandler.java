package handlers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import email.JourneyMail;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Validatable;
import org.apache.commons.io.FileUtils;
import store.InkstepStore;

public class JourneyImagesCreateHandler
  extends AbstractRequestHandler<JourneyImagesCreateHandler.Payload> {

  public JourneyImagesCreateHandler(InkstepStore store) {
    super(Payload.class, store);
  }

  synchronized
  @Override
  protected Answer processImpl(Payload request, Map<String, String> urlParams) {
    System.out.println("Received img for journey " + request.getJourneyId());

    int imageId = store.putJourneyImage(request.getJourneyId(),
      request.getImage());

    boolean sendEmail = store.hasGotAllImages(request.getJourneyId());

    if (sendEmail) {
      System.out.println("SENDING EMAIL!");

      List<String> imageData = store.getImagesFromJourneyId(request.getJourneyId());
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

      JourneyMail.sendNewTattooRequestEmail(
        store,
        store.getJourneyFromId(request.getJourneyId()),
        images
      );
    }

    Map<String, String> responseMap = new HashMap<String, String>() {{
      put("image_id", String.valueOf(imageId));
    }};

    return Answer.ok(dataToJson(responseMap));
  }

  static class Payload implements Validatable {

    private int journeyId;
    private String image;

    @JsonCreator
    Payload(@JsonProperty("journey_id") int journeyId,
      @JsonProperty("image_data") String image) {
      this.journeyId = journeyId;
      this.image = image;
    }

    public String getImage() {
      return image;
    }

    public int getJourneyId() {
      return journeyId;
    }

    @Override
    public boolean isValid() {
      return journeyId >= 0 && image.length() > 0;
    }
  }
}
