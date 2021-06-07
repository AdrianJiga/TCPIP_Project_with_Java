// TCP/IP Client program                                                        
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args)throws Exception {

        String line, newLine;
        DataInputStream in=new DataInputStream(System.in);

        // Communication Endpoint for client and server
        Socket cs=new Socket("LocalHost",6789);
        System.out.println("Client Started...");

        // DataInputStream to read data from input stream
        DataInputStream inp=new DataInputStream (cs.getInputStream());

        // DataOutputStream to write data on output stream
        DataOutputStream out=new DataOutputStream(cs.getOutputStream());

        while(true) {

            newLine = in.readLine();

            if (newLine.equals("q")) {
                out.writeBytes("Client is down..." +'\n');
                return;
            } else {
                out.writeBytes(newLine + '\n');
            }

            line = inp.readLine();
            System.out.println("Received from server: " + line);
        }
    }
}     