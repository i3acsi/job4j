package ru.job4j.oracle.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private int servPort;
    private String ip; //"127.0.0.1" - localHost

    public Client(int servPort, String ip) {
        this.servPort = servPort;
        this.ip = ip;
    }

    /*
    Сервер должен отвечать на простые вопросы. Если Оралку сказали пока. То приложение выключается.
    Важно. что Оракл может отправлять большие сообщения. Что бы понять когда конец сообщение он отправляет пустую строку.
     */

    public void connect() {
        try {
            String msg;
            Socket socket = new Socket(InetAddress.getByName(ip), servPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner console = new Scanner(System.in);
            do {
                msg = console.next();
                out.println(msg);
                String str = in.readLine();
                while (!(str.isEmpty())) {
                    System.out.println(str);
                    str = in.readLine();
                }
            } while (!"exit".equals(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client(5000, "127.0.0.1");
        client.connect();
    }
}
