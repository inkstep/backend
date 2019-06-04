package email;

import model.Artist;
import model.Journey;
import model.Studio;
import model.User;
import store.InkstepStore;

import javax.mail.MessagingException;
import java.util.ArrayList;

public class JourneyMail {
    private InkstepStore store;
    private Journey journey;

    public JourneyMail(InkstepStore store, Journey journey) {
        this.store = store;
        this.journey = journey;
    }

    public void sendRequestEmail() {
        Artist artist = store.getArtistFromId(journey.artistID);
        User user = store.getUserFromId(journey.userID);
        Studio studio = store.getStudioFromId(artist.studioID);
        String emailTemplate =
                "Client request for " + artist.name + " from " + studio.name
                        + "\n" + "Hi, " + artist.name
                        + "!\n" + "You have received a new client request from " + user.name
                        + "!\n\n" + user.name
                        + " would love to get a " + journey.tattooDesc + " on their " + journey.position
                        + " about " + journey.size
                        + " large.\n" + user.name + " is available on "
                        + journey.humanAvaliability() + " and " + (
                        journey.deposit.equals("1") ? "is" : "is not")
                        + " willing to leave a deposit\n\n"
                        + "If you would like to get in touch with " + user.name + " their "
                        + "email " + "is " + user.email
                        + ", or simply reply to this email!\n\n" + "Happy tattoo'ing!\n\n"
                        + "Sent from Inkstep on behalf of " + user.name;

        System.out.println(emailTemplate);

        JavaEmail javaEmail = new JavaEmail();

        try {
            javaEmail.sendEmail(artist.email, emailTemplate, "Client Request", user.email,
                    new ArrayList<>());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
