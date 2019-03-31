package sample;
import java.io.*;
import java.net.*;

public class Client {



    private Socket clientSocket;
    private PrintWriter out;


    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String msg) {
        out.println(msg);

    }

}
