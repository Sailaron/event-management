package Events_App.Web;

import Events_App.Domain.*;
import Events_App.EventsApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class EventController {
	@Autowired
	private EventRepository eventRepository;

	@GetMapping("/events")
	public String getDefaultEntityPage(ModelMap modelMap) {
		modelMap.addAttribute("calendar", String.valueOf(java.time.LocalDate.now()));
		return "events";
	}

	@GetMapping("/events/{date}")
	public String getEntityPage(ModelMap modelMap, @PathVariable String date) {
		modelMap.addAttribute("calendar",date);
		return "events";
	}

	@GetMapping("/events/{date}/{event}")
	public String getSpecifiedEntityPage(ModelMap modelMap, @PathVariable String date, @PathVariable String event) {
		LocalDate dateObj = LocalDate.parse(date);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM uuuu", Locale.ENGLISH);
		String dateString = dtf.format(dateObj);

		modelMap.addAttribute("date", dateString);
		modelMap.addAttribute("calendar", date);
		modelMap.addAttribute("event", event);
		return "event";
	}

	@GetMapping("/api/event/add")
	public @ResponseBody String addEvent(String token, String title, String time, String date) {
		if (!token.equals(EventsApplication.TOKEN)) {
			return "false";
		}

		if (!date.equals("")) {
			Event event;
			if (!time.equals(""))
				event = new Event(title, date, time);
			else
				event = new Event(title, date);

			eventRepository.save(event);
			return "Added";
		}

		return "Some field is empty";
	}

	@GetMapping("/api/event/participation/all")
	public @ResponseBody String loadAllParticipationForEvent(String token, Integer eventId) {
		if (!token.equals(EventsApplication.TOKEN)) {
			return "false";
		}

		if (eventId == null) {
			return "false";
		}

		Event event = eventRepository.findById(eventId).orElse(null);
		assert event != null;
		Iterable<Participation> test = event.getParticipation();

		if (event != null)
			return event.getParticipation().toString();
		else
			return "false";
	}

	@GetMapping("/api/event/all")
	public @ResponseBody String loadAllEvents(String token, String date) {
		if (!token.equals(EventsApplication.TOKEN)) {
			return "false";
		}

		if (date == null) {
			return "false";
		}

		Iterable<Event> allEvents = eventRepository.findByDate(date);

		return allEvents.toString();
	}

	@GetMapping("/api/event/get/filtered")
	public @ResponseBody String loadSpecificEvent(String token, String date, Integer eventId) {
		if (!token.equals(EventsApplication.TOKEN)) {
			return "false";
		}

		if ((eventId == null) || (date == null)) {
			return "false";
		}

		Event event = eventRepository.findById(eventId).orElse(null);
		assert event != null;
		if (event.getDate().equals(date))
			return event.toString();
		else
			return "false";
	}
}
