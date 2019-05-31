package ru.job4j.socket.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private int servPort;
    private String ip; //127.0.0.1 - localHost

    public Client(int servPort, String ip) {
        this.servPort = servPort;
        this.ip = ip;
    }

    public void connect() {
        //попытаемя подключиться к серверу
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            System.out.printf("Connect to server: %d%n", servPort);

            //на стороне клиента так же необходим сокет
            Socket socket = new Socket(ip, servPort);

            InputStream sctIS = socket.getInputStream();
            OutputStream sctOS = socket.getOutputStream();

            //преобразуем байтовые стримы в DataStream
            DataInputStream inS = new DataInputStream(sctIS);
            DataOutputStream outS = new DataOutputStream(sctOS);

            //понадобится еще один поток, который будет считывать данные
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String string;
            System.out.println("Input a phrase to send to the server");
            while (true) {
                string = reader.readLine();
                outS.writeUTF(string); // отсылаем на сервер
                outS.flush(); // закрываем поток
                string = inS.readUTF(); // и ожидаем ответ от сервера
                System.out.printf("Server replied with a message: %s%n", string); // и выводим полученное сообщение
                System.out.println("Input next message");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client(5000, "127.0.0.1");
        client.connect();
    }
}
