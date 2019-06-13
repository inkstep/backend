package notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import model.Journey;
import model.JourneyStage;
import model.User;
import store.InkstepStore;

public class UserNotifier {
  public User user;

  public UserNotifier(User user) {
    this.user = user;
  }

  public boolean notifyStage(Journey journey, JourneyStage stage) {

    Message message = Message.builder()
      .putData("journey", String.valueOf(journey.journeyID))
      .setNotification(
        new Notification(
          "I've successfully sent a message",
          stage.name() + "is updated!")
      )
      .setToken(user.token)
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
