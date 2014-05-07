package out;

import java.util.List;

import logmanager.Configuration;
import logmanager.QueueManager;
import logmanager.Event;

public interface OutputAdapter 
{
	public void setupConfig(Configuration config);
	//boolean storeEvents(List<Event> batch);
	public String test(String tmp);
	public void exec();
}

