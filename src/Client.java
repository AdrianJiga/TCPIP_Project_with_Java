import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Socket socket = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        BufferedWriter  bufferedWriter = null;

        try {

            socket = new Socket("localhost", 1234);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            Scanner scanner = new Scanner(System.in);

            while (true) {

                String messageToSend = scanner.nextLine();
                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush(); //flushes the stream because otherwise the stream flushes only when it's full
                System.out.println("Server said: " + bufferedReader.readLine());
                if (messageToSend.equalsIgnoreCase("Bye")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (socket != null)
                socket.close();
                if (inputStreamReader != null)
                inputStreamReader.close();
                if (outputStreamWriter != null)
                outputStreamWriter.close();
                if (bufferedReader != null)
                bufferedReader.close();
                if (bufferedWriter != null)
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}