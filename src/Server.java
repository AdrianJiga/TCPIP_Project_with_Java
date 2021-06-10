import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        // a server socket waits for requests to come in over the network. Our client is trying to connect to port 1234.
        // Thus, we want our server socket to be waiting for a connection on port 1234.
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(1234);

        //this while loop is to accept a new client connection
        while (true) {
            try {

                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                //this while loop is for constantly sending messages. It disconnects when the client says "Bye"
                while (true) {

                    String messageFromClient = bufferedReader.readLine();
                    System.out.println("Client said: " + messageFromClient);
                    bufferedWriter.write("Message Received.");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    if (messageFromClient.equalsIgnoreCase("Bye")) {
                        break;
                    }
                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}