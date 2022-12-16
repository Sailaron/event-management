package Events_App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventsApplication {
    // Simple token to get reed of most of the web surfing robots
    //TODO: For better security, you can implement authorisation with user's session token generation and/or captcha checking
    public static String TOKEN = "jWnZr4u7x!AD*G-JaNdRgUkXp2s5v8y";

    public static void main(String[] args) {
        SpringApplication.run(EventsApplication.class, args);
    }

}
