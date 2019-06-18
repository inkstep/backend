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

    String artistName = store.getArtistFromID(journey.artistID).name;
    String newInfo;
    if (stage == JourneyStage.QuoteReceived) {
      newInfo = " quote ";
    } else {
      newInfo = "n appointment date ";
    }

    ApnsConfig appleConfig = ApnsConfig.builder().setAps(
            Aps.builder().setBadge(1).setAlert(
                    ApsAlert.builder().setTitle(artistName + "has sent an update!")
                      .setBody("A" + newInfo + "has been sent for your "
                               + journey.tattooDesc + " tattoo.")
                      .build()
            ).build())
    .putCustomData("journey", String.valueOf(journey.journeyID))
    .build();

    Message message = Message.builder()
      .putData("journey", String.valueOf(journey.journeyID))
      .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
      .setNotification(
        new Notification(
          artistName + "has sent an update!",
                "A" + newInfo + "has been sent for your " + journey.tattooDesc + " tattoo.")
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
