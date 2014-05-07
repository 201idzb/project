package in;

import logmanager.Configuration;
import logmanager.QueueManager;

public interface InputAdapter 
{
	public void setupConfig(Configuration config);
	public void connectToQueueManager(QueueManager queue);
	public String test(String tmp);
	public void exec();
}

