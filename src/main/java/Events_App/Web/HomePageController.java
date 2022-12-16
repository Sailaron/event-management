package Events_App.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
	@RequestMapping(value={"/"})
	public String getEntityPage() {
		return "homepage";
	}
}