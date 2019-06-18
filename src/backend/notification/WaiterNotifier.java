package notification;

import model.Artist;
import model.Journey;
import model.JourneyStage;
import model.User;
import store.InkstepStore;

import java.util.ArrayList;
import java.util.List;

public class WaiterNotifier {
  private InkstepStore store;
  private List<Journey> waitingList;
  public List<Integer> notified;

  public WaiterNotifier(InkstepStore store) {
    this.store = store;
    this.notified = new ArrayList<>();
  }

  public int getFailures() {
    return waitingList.size() - getSuccesses();
  }

  public int getSuccesses() {
    return notified.size();
  }

  public WaiterNotifier newSlotUsing(Journey removedJourney) {
    waitingList = store.getWaitingListJourneysFromArtistId(removedJourney.artistID);
    for (Journey j: waitingList) {
      User u = store.getUserFromID(j.userID);
      Artist a = store.getArtistFromID(j.artistID);

      UserNotifier un = new UserNotifier(u);
      boolean success = un.cancellation(a, j, removedJourney);
      if (success) {
        notified.add(j.journeyID);
        store.offerAppointment(j.journeyID, removedJourney.bookingDate);
        store.updateStage(j.journeyID, JourneyStage.AppointmentOfferReceived);
      }
    }
    return this;
  }

  public WaiterNotifier slotFilledBy(User successfulUser, Journey journeyFiller) {
    waitingList = store.getJourneysWithOfferedSlot(journeyFiller.artistID, journeyFiller.bookingDate);
    for (Journey j : waitingList) {
      User u = store.getUserFromID(j.userID);
      Artist a = store.getArtistFromID(j.artistID);

      UserNotifier un = new UserNotifier(u);
      boolean success = un.filled(successfulUser, a, j);
      if (success) {
        notified.add(j.journeyID);
        store.updateStage(j.journeyID, JourneyStage.WaitingList);
      }
    }
    return this;
  }
}
