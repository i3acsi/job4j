package ru.job4j.sea.newseabattle;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SingleGame {
    private SimplePlayer player1;
    private SimplePlayer player2;

    private InputStream inputStream;
    private OutputStream outputStream;

    private Map<String, Integer> map;
    private Map<Integer, String> states;
    private Consumer<String> out = System.out::println;
    private InputStream in = (System.in);

    public SingleGame() {
        initMaps();
        initPlayers();
    }

    public void startGame() {
        while (!player2.isLose() && !player1.isLose()) {
            playersTurn(player1, player2);
            if (player2.isLose()) break;
            playersTurn(player2, player1);
        }
        if (player1.isLose()) player2.congratulations();
        else player1.congratulations();
        try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playersTurn(SimplePlayer me, SimplePlayer other) {
        boolean hit = true;
        me.display(other);
        while (hit && !other.isLose()) {
            int[] coordinate = me.shoot();
            me.accept(coordinate);
            other.accept(coordinate);
            int result = other.acceptDamage(coordinate[0], coordinate[1]);
            me.shootResultAction(result);
            me.display(other);
            other.display();
            hit = result < 4;
            sleep();
        }
    }

    private void initPlayers() {
        IInput iInput = new ConsoleInput(in, out);
        IDisplayStrategy displayStrategy = new ConsoleDisplay(map, states, out, iInput);
        boolean reverse = false;
        String ip = null;
        int mode = displayStrategy.askMode();
        if (mode == 3) ip = displayStrategy.askIp();
        player1 = new HumanPlayer(10, "Human", displayStrategy);
        player1.prepare();
        if (mode == 3) {
            int port = 1100;
            if ("".equals(ip)) {
                Server server = new Server(port);
                server.startServer();
            } else {
                reverse = true;
                Client client = new Client(port, ip);
                client.connect();
            }

            player2 = new NetPlayer(10, "netPlayer", new EmptyDisplay(), inputStream, outputStream);
            try {
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                oos.writeObject(player1.getTable());
                oos.flush();
            } catch (IOException ioe) {
                displayStrategy.accept(ioe.getMessage());
            }
        }


        if (mode == 2) player2 = new PCPlayer(10, "player2_PC", displayStrategy);

        if (mode == 1) player2 = new HumanPlayer(10, "player2_Man", displayStrategy);
        player2.prepare();

        if (reverse) {
            SimplePlayer tmp = player2;
            player2 = player1;
            player1 = tmp;
            tmp = null;
        }
    }


    private void initMaps() {
        map = new HashMap<>(17);
        map.put("А", 0);
        map.put("Б", 1);
        map.put("В", 2);
        map.put("Г", 3);
        map.put("Д", 4);
        map.put("Е", 5);
        map.put("Ж", 6);
        map.put("З", 7);
        map.put("И", 8);
        map.put("К", 9);
        states = new HashMap<>(17);
        states.put(0, "_"); //new String("_".getBytes("Cp1251"))
        states.put(1, "К"); //new String("█".getBytes("Cp1251"))
        states.put(2, "Р");
        states.put(3, "У");
        states.put(4, "М");
    }

    public static void main(String[] args) {
        SingleGame game = new SingleGame();
        game.startGame();
    }


    class Server {
        private int port;

        public Server(int port) {
            this.port = port; //1025 - 65535
        }

        public void startServer() {
            try {
                ServerSocket srvSocket = new ServerSocket(port);
                player1.accept("Waiting server connection");
                Socket socket = srvSocket.accept();
                player1.accept("Connection succeed: " + socket.isConnected());
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Client {
        private int servPort;
        private String ip; //127.0.0.1 - localHost

        public Client(int servPort, String ip) {
            this.servPort = servPort;
            this.ip = ip;
        }

        public void connect() {
            try {
                InetAddress inetAddress = InetAddress.getByName(ip);
                player1.accept(String.format("Connect to server: %d%n", servPort));
                Socket socket = null;
                boolean flag = false;
                while (!flag) {
                    try {
                        socket = new Socket(ip, servPort);
                        flag = true;
                    } catch (ConnectException e) {
                        player1.accept(e.getMessage());
                    }
                    sleep();
                }

                player1.accept("Connection succeed: " + socket.isConnected());
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}