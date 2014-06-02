package in;

import logmanager.Configuration;
import logmanager.QueueManager;

public interface InputAdapter {
	void setupConfig(Configuration config);
	void connectToQueueManager(QueueManager queue);
	String test(String tmp);
	void exec();
}