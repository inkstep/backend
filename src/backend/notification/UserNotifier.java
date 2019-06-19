package notification;

import com.google.firebase.messaging.*;
import model.Artist;
import model.Journey;
import model.JourneyStage;
import model.User;

public class UserNotifier {
  public User user;

  public UserNotifier(User user) {
    this.user = user;
  }

  public boolean notifyStage(Artist artist, Journey journey, JourneyStage stage) {

    String title = artist.name + " has sent an update!";
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
            ).setSound("default").build())
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

  public boolean cancellation(Artist artist, Journey waitingJourney, Journey cancelledJourney) {

    String title = artist.name + " has a cancellation and can see you!";
    String body = "Are you free on" + cancelledJourney.bookingDate;

    ApnsConfig appleConfig = ApnsConfig.builder().setAps(
      Aps.builder().setBadge(1).setAlert(
        ApsAlert.builder()
          .setTitle(title)
          .setBody(body)
          .build()
      ).build())
      .putCustomData("journey", String.valueOf(waitingJourney.journeyID))
      .build();

    Message message = Message.builder()
      .putData("journey", String.valueOf(waitingJourney.journeyID))
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
    System.out.println("Sent notification due to cancellation: " + response);
    return true;
  }

  public boolean filled(User successfulUser, Artist artist, Journey journey) {
    
    String title = successfulUser.name + " got the slot!";
    String body = "The slot released by " + artist.name + " has been filled. " +
            "Don't worry there will be a next time.";

    ApnsConfig appleConfig = ApnsConfig.builder().setAps(
      Aps.builder().setBadge(1).setAlert(
        ApsAlert.builder()
          .setTitle(title)
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
    String response;
    try {
      response = FirebaseMessaging.getInstance().send(message);
    } catch (FirebaseMessagingException e) {
      e.printStackTrace();
      return false;
    }
    System.out.println("Sent notification due to getting the slot: " + response);
    return true;
  }
}
