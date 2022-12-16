package Events_App.Service;

import Events_App.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InitialDataService {
    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        try {
            //--- Simple check that DB is empty ----

            Iterable<Visitor> allVisitors = visitorRepository.findAll();
            int visitorsCount = ((Collection<?>) allVisitors).size();
            if (visitorsCount > 0)
                return; // Skipping sample objects creation, because DB is not empty

            Iterable<Event> allEvents = eventRepository.findAll();
            int eventsCount = ((Collection<?>) allEvents).size();
            if (eventsCount > 0)
                return; // Skipping sample objects creation, because DB is not empty

            //----------- Sample Events ------------
            String currentDate = String.valueOf(java.time.LocalDate.now());
            System.out.println(currentDate); // 2022-11-23
            Map<String, Event> newEvents = new HashMap<>();

            Event eventOne = new Event("Tea drinking at 9:00 am", currentDate);
            Event eventTwo = new Event("Playing football at 2:00 pm", currentDate);
            Event eventThree = new Event("Book reading at 7:30 pm", currentDate);

            newEvents.put("1", eventOne);
            newEvents.put("2", eventTwo);
            newEvents.put("3", eventThree);

            eventRepository.saveAll(newEvents.values());

            //----------- Sample Visitors ------------

            Map<String, Visitor> newVisitors = new HashMap<>();

            Visitor visitorOne = new Visitor("Caesar", "Julius");

            Visitor visitorTwo = new Visitor("Georg", "Hegel");

            Visitor visitorThree = new Visitor("Tom", "Fishman");

            newVisitors.put("1", visitorOne);
            newVisitors.put("2", visitorTwo);
            newVisitors.put("3", visitorThree);

            visitorRepository.saveAll(newVisitors.values());

            //----------- Sample Participation ------------

            Map<String, Participation> newParticipation = new HashMap<>();

            Participation participationOneOfVisitorOne = new Participation(visitorOne, eventOne, "Yes");

            Participation participationTwoOfVisitorOne = new Participation(visitorOne, eventTwo, "No");

            Participation participationOneOfVisitorTwo = new Participation(visitorTwo, eventTwo, "Yes");

            Participation participationTwoOfVisitorTwo = new Participation(visitorTwo, eventThree, "Yes");

            Participation participationOneOfVisitorThree = new Participation(visitorThree, eventOne, "No");

            Participation participationTwoOfVisitorThree = new Participation(visitorThree, eventThree, "No");

            newParticipation.put("1", participationOneOfVisitorOne);
            newParticipation.put("2", participationTwoOfVisitorOne);
            newParticipation.put("3", participationOneOfVisitorTwo);
            newParticipation.put("4", participationTwoOfVisitorTwo);
            newParticipation.put("5", participationOneOfVisitorThree);
            newParticipation.put("6", participationTwoOfVisitorThree);


            participationRepository.saveAll(newParticipation.values());

        } catch (Exception ignored) {

        }
    }
}
