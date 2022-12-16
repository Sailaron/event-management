package Events_App.Web;

import Events_App.Domain.*;
import Events_App.EventsApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class VisitorController {

	@Autowired
	private VisitorRepository visitorRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ParticipationRepository participationRepository;

	@GetMapping("/visitors")
	public String getEntityPage() {
		return "visitors";
	}

	@GetMapping(/*value = */"/api/visitor/all"/*, consumes = "application/json", produces = "application/json"*/) //TODO: Fix this
	public @ResponseBody String loadAllVisitors(String token) {
		if (!token.equals(EventsApplication.TOKEN)) {
			return "false";
		}

		Iterable<Visitor> allVisitors = visitorRepository.findAll();

		return allVisitors.toString();
	}

	@GetMapping("/api/visitor/remove")
	public @ResponseBody String removeSelectedVisitor(String token, Integer id) {
		if (!token.equals(EventsApplication.TOKEN)) {
			return "false";
		}

		if (visitorRepository.existsById(id)) {
			visitorRepository.deleteById(id);
			return "Deleted";
		}

		return "Not found!";
	}

	@GetMapping("/api/visitor/add")
	public @ResponseBody String addVisitor(String token, String name, String surname) {
		if (!token.equals(EventsApplication.TOKEN)) {
			return "false";
		}

		if (!name.equals("") && !surname.equals("")) {
			Visitor visitor = new Visitor(name, surname);
			visitorRepository.save(visitor);
			return "Added";
		}

		return "Some field is empty";
	}

	@GetMapping("/api/visitor/edit")
	public @ResponseBody String editSelectedVisitor(String token, Integer id, String name, String surname) {
		if (!token.equals(EventsApplication.TOKEN)) {
			return "false";
		}

		if (visitorRepository.existsById(id)) {
			Visitor visitor = visitorRepository.findById(id).orElse(null);
			if (visitor != null) {
				visitor.setName(name);
				visitor.setSurname(surname);
				visitorRepository.save(visitor);
				return "Edited";
			}
		}

		return "Not found!";
	}

	@GetMapping("/api/visitor/participation/delete")
	public @ResponseBody String unsetParticipationForSelectedVisitor(String token, Integer eventId, Integer visitorId) {
		if (!token.equals(EventsApplication.TOKEN)) {
			return "false";
		}

		if (eventRepository.existsById(eventId)) {
			if (visitorRepository.existsById(visitorId)) {
				Event event = eventRepository.findById(eventId).orElse(null);
				Visitor visitor = visitorRepository.findById(visitorId).orElse(null);

				try {
					Participation participation = participationRepository.findByEventAndVisitor(event, visitor);
					participationRepository.deleteById(participation.getId());
					return "Deleted";
				} catch (Exception ignored) {}
			}
		}

		return "false";
	}

	@GetMapping("/api/visitor/participation/edit")
	public @ResponseBody String editParticipationForSelectedVisitor(String token, Integer eventId, Integer visitorId, String status, Integer participationId) {
		if (!token.equals(EventsApplication.TOKEN)) {
			return "false";
		}

		if ((!status.equals("Yes")) && (!status.equals("No"))) {
			return "false";
		}

		Participation participation;
		if (participationId != null) {
			if (participationRepository.existsById(participationId)) {
				participation = participationRepository.findById(participationId).orElse(null);
				assert participation != null;
				participation.setStatus(status);
				participationRepository.save(participation);
				return "Edited to " + status;
			}
		} else {
			if ((eventRepository.existsById(eventId)) && (visitorRepository.existsById(visitorId))) {
				Event event = eventRepository.findById(eventId).orElse(null);
				Visitor visitor = visitorRepository.findById(visitorId).orElse(null);

				participation = new Participation(visitor, event, status);
				participationRepository.save(participation);
				return "Created and Edited to " + status;
			}
		}

		return "false";
	}
}
