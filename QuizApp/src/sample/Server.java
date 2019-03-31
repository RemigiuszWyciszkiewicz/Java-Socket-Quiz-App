package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    AnswerHandler answerHandler;

    public Server(AnswerHandler answerHandler) {
        this.answerHandler = answerHandler;
    }

    public void start(int port) {




        try {
            serverSocket = new ServerSocket(port);
            while(true)
                new EchoClientHandler(serverSocket.accept()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
        class EchoClientHandler extends Thread {

            private Socket clientSocket;
            private BufferedReader in;
            private PrintWriter out;

            public EchoClientHandler(Socket socket) {
                this.clientSocket = socket;
            }

            public void run() {
                try {

                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                   // out = new PrintWriter(clientSocket.getOutputStream(), true);
                    String inputLine;


                    while ((inputLine = in.readLine()) != null) {

                       // System.out.println(inputLine);
                        try {
                            AnswerHandler.answersQueue.put(new Answer(
                                    inputLine.substring(inputLine.indexOf("-")+1)
                                    ,inputLine.substring(0,inputLine.indexOf("-"))
                            ));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                       // out.println(answerHandler.showQuestion());

                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }






