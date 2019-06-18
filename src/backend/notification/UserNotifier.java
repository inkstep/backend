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

    String newInfo;
    if (stage == JourneyStage.QuoteReceived) {
      newInfo = " quote ";
    } else {
      newInfo = "n appointment date ";
    }

    ApnsConfig appleConfig = ApnsConfig.builder().setAps(
            Aps.builder().setBadge(1).setAlert(
                    ApsAlert.builder().setTitle(artist.name + "has sent an update!")
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
          artist.name + "has sent an update!",
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

  public boolean cancellation(Artist artist, Journey waitingJourney, Journey cancelledJourney) {
    ApnsConfig appleConfig = ApnsConfig.builder().setAps(
      Aps.builder().setBadge(1).setAlert(
        ApsAlert.builder()
          .setTitle(artist.name + " has a cancellation and can see you!")
          .setBody("Are you free on" + cancelledJourney.bookingDate)
          .build()
      ).build())
      .putCustomData("journey", String.valueOf(waitingJourney.journeyID))
      .build();

    Message message = Message.builder()
      .putData("journey", String.valueOf(waitingJourney.journeyID))
      .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
      .setNotification(
        new Notification(
          artist.name + " has a cancellation and can see you!",
          "Are you free on " + cancelledJourney.bookingDate)
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
    ApnsConfig appleConfig = ApnsConfig.builder().setAps(
      Aps.builder().setBadge(1).setAlert(
        ApsAlert.builder()
          .setTitle(successfulUser.name + " got the slot!")
          .setBody("The slot released by " + artist.name +" has been filled. " +
            "Don't worry there will be a next time.")
          .build()
      ).build())
      .putCustomData("journey", String.valueOf(journey.journeyID))
      .build();

    Message message = Message.builder()
      .putData("journey", String.valueOf(journey.journeyID))
      .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
      .setNotification(
        new Notification(
          successfulUser.name + " got the slot!",
          "The slot released by " + artist.name +" has been filled. " +
            "Don't worry there will be a next time.")
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
