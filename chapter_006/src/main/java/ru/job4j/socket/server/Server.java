package ru.job4j.socket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port; //1025 - 65535
    }

    public void init() {
        try {
            ServerSocket srvSocket = new ServerSocket(port);
            System.out.println("Waiting server connection");

            //заставим наш сервер ждать подключение какого-нибудь клиента
            Socket socket = srvSocket.accept();

            System.out.println("Connection succeed");

            InputStream sctIS = socket.getInputStream();
            OutputStream sctOS = socket.getOutputStream();

            //преобразуем байтовые стримы в DataStream
            DataInputStream inS = new DataInputStream(sctIS);
            DataOutputStream outS = new DataOutputStream(sctOS);

            //будем передавать текстовые сообщения
            String string;
            while (true) {
                string = inS.readUTF();
                System.out.printf("Received string: %s%n", string);
                System.out.println("Send the string back");
                outS.writeUTF(string);
                outS.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
        server.init();
    }
}
