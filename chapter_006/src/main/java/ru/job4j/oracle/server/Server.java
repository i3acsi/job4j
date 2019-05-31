package ru.job4j.oracle.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    //private int port;
    private Socket socket;
    private Message message;
    private Random random;

    public Server(Socket socket, String file) {
        this.socket = socket; //1025 - 65535
        this.message = new Message(file);
        this.random = new Random();
    }

    /*
    Сервер должен отвечать на простые вопросы. Если Оралку сказали пока. То приложение выключается.
    Важно. что Оракл может отправлять большие сообщения. Что бы понять когда конец сообщение он отправляет пустую строку.
     */
    public void init() {
        String ask;
        try {
            //Socket socket = new ServerSocket(port).accept();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("hello".equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else if (!"exit".equals(ask)) {
                    int loop = random.nextInt(10);
                    while (loop != 0) {
                        out.println(message.getAnswer());
                        loop--;
                    }
                    out.println();
                } else {
                    out.println("goodbye");
                    out.println("");
                }
            } while (!"exit".equals(ask));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = null;
        try (final Socket socket = new ServerSocket(5000).accept()) {
            server = new Server(socket, "./text.txt");
            server.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
