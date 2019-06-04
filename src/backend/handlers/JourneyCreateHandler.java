package handlers;

import email.JourneyMail;
import model.Journey;
import store.InkstepStore;

import java.util.Map;

public class JourneyCreateHandler extends AbstractRequestHandler<Journey> {

    private InkstepStore store;

    public JourneyCreateHandler(InkstepStore store) {
        super(Journey.class, store);
        this.store = store;
    }

    @Override
    protected Answer processImpl(Journey journey, Map<String, String> urlParams, boolean shouldReturnHtml) {
        int id = store.createJourney(journey);

        new JourneyMail(store, journey).sendRequestEmail();

        return Answer.ok(String.valueOf(id));
    }
}