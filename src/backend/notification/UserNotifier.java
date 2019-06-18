package notification;

import com.google.firebase.messaging.*;
import model.Journey;
import model.JourneyStage;
import model.User;
import store.InkstepStore;

public class UserNotifier {
  public User user;

  public UserNotifier(User user) {
    this.user = user;
  }

  public boolean notifyStage(InkstepStore store, Journey journey, JourneyStage stage) {

    String title = store.getArtistFromID(journey.artistID).name + "has sent an update!";
    String body = "A";
    if (stage == JourneyStage.QuoteReceived) {
      body += " quote ";
    } else {
      body += "n appointment date ";
    }
    body += "has been sent for your " + journey.tattooDesc + " tattoo.";

    ApnsConfig appleConfig = ApnsConfig.builder().setAps(
            Aps.builder().setBadge(1).setAlert(
                    ApsAlert.builder().setTitle(title)
                      .setBody(body)
                      .build()
            ).build())
    .putCustomData("journey", String.valueOf(journey.journeyID))
    .build();

    Message message = Message.builder()
      .putData("journey", String.valueOf(journey.journeyID))
      .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
      .setNotification(
        new Notification(
          title,
                body)
      )
      .setToken(user.token)
      .setApnsConfig(appleConfig)
      .build();

    // Send a message to the device corresponding to the provided
    // registration token.
    String response = null;
    try {
      response = FirebaseMessaging.getInstance().send(message);
    } catch (FirebaseMessagingException e) {
      e.printStackTrace();
      return false;
    }
    System.out.println("Sent notification due to update stage: " + response);
    return true;
  }
}
