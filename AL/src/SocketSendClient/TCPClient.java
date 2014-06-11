package SocketSendClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * TCPClient do wysylania logow.
 * @author Albert Kalkowski
 *
 */



public final class TCPClient {
/**
 * konstuktor.
 */
private TCPClient() {
    // TODO Auto-generated constructor stub
}

/**
 * metoda main klienta wysylania logow.
 * @param args argument
 * @throws IOException  wyjatek polaczenia tcp
 */
    public static void main(final String[] args) throws IOException {

        String hostName = "127.0.0.1";
      final  int portNumber = 2106;
      final int timeout = 1000;
        File file = new File(
                "D:\\server.log.1"); // lokalizacja pliku z logiem do wyslania
        try {

            @SuppressWarnings("resource")
            Socket echoSocket = new Socket();
            echoSocket.connect(new InetSocketAddress(hostName,
                    portNumber),
                    timeout);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),
                    true);

            @SuppressWarnings("resource")
            BufferedReader stdIn = new BufferedReader(new FileReader(file));
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
System.out.println(userInput);
            }

        } catch (Exception e) {
            System.out.println("err " + e);
            System.out.println("serwer niestostepny");
        }

    }
}
