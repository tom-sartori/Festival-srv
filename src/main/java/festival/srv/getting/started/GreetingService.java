package festival.srv.getting.started;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

	public String greeting(String name) {
		return "Welcome " + name;
	}
}