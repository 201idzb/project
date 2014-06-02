package out;

import java.util.List;

import logmanager.Configuration;
import logmanager.Event;

public interface OutputAdapter {
	void setupConfig(Configuration config);
	boolean storeEvents(List<Event> batch);
	String test(String tmp);
	void exec();
}