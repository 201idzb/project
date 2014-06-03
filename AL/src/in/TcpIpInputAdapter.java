/*Unstable */


package in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import logmanager.Configuration;
import logmanager.Event;
import logmanager.QueueManager;

public class TcpIpInputAdapter extends Thread implements InputAdapter {
	
	private Configuration config;
	private QueueManager queue;

	public TcpIpInputAdapter() {
		System.out.println("Utworzono Testowy Adapter Wejściowy");
	}

	public void setupConfig(Configuration config) {
		this.config = config;
	}

	public void connectToQueueManager(QueueManager queue) {
		this.queue = queue;
	}

	public String test(String tmp) {
		return tmp;
	}

	public void exec() {
		start();
	}

	public int setIterations(int index) {
		this.iterations = index;
		return iterations;
	}

	public String setData(String data) {
		this.data = data;
		return data;
	}

    private String data = null;
    private String[] timestamp = null;
    private String[] loglevel = null;
    private String[] details = null;
    private String[] parts;
	private int iterations = 0;

	public void run() {
		
		

		int portNumber = 2106;
		while (true) {

			try (ServerSocket serverSocket = new ServerSocket(portNumber);

					Socket clientSocket = serverSocket.accept();
					PrintWriter out = new PrintWriter(
							clientSocket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));) {
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					System.out.println(inputLine);
					 timestamp = new String[(int) config.getBatchSize()];
					    loglevel = new String[(int) config.getBatchSize()];
					    details = new String[(int) config.getBatchSize()];
					
					    for (int i = 0; i < config.getBatchSize(); ++i) {	
					    	//jezeli nie ma nastepnej linii to konczy
					  
							data = inputLine;
							parts = data.split(" "); //wyszukiwanie spacji
							
							//jesli nie jest to pusta linia
							if (!(data.equals(""))) { 
								timestamp[i] = data.substring(1, 0 + parts[0].length() - 1);
								
								loglevel[i] = data.substring(parts[0].length() + 1,
										parts[0].length() + 1 + parts[1].length());
								
								details[i] = data.substring(parts[0].length() 
										+ parts[1].length() + 3,
										data.length());
							}
						}
					    
					    //tworzenie zdarzen
					    for (int i = 0; i < config.getBatchSize(); ++i) {
					    	if (timestamp[i] == null) { break; }
							Event a = new Event(timestamp[i], loglevel[i], details[i]);
							System.out.println((queue.acceptEvent(a)) 
								? "Dodano Event(" + timestamp[i] + "," + " " 
									+ loglevel[i] + "," + " " 
									+ details[i] + ")" 
								: "Nie udalo sie dodac Eventu(" + timestamp[i] + "," + " " 
									+ loglevel[i] + "," + " " 
									+ details[i] + ")");
						}
					}
					System.out.println("Odczytano plik!");
					
			    

			} catch (IOException e) {
				System.out
						.println("Exception caught when trying to listen on port "
								+ portNumber + " or listening for a connection");
				System.out.println(e.getMessage());
			}
		}

	}

}
