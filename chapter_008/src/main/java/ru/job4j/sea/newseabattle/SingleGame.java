package ru.job4j.sea.newseabattle;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SingleGame {
    private SimplePlayer player1;
    private SimplePlayer player2;

    private Consumer<String> consumer;
    private Supplier<String> supplier;
    private IDisplayStrategy displayStrategy;
    private IInput iInput;
    private Map<String, Integer> map;
    private Map<Integer, String> states;
    private Consumer<String> out = System.out::println;
    private InputStream in = (System.in);

    public SingleGame(int size) {
        //System.setProperty("console.encoding", "Cp866");
        try {
            initMaps();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        initPlayers();
    }

    public void startGame() {
//        while (true) {
//            int[] c = player1.shoot();
//            player1.displayStrategy.accept(c);
//            int res = player2.acceptDamage(c[0], c[1]);
//            player1.shootResultAction(res);
//            c = player2.shoot();
//            player1.acceptDamage(c[0], c[1]);
//        }
        while (!player2.isLose() && !player1.isLose()) {
            playersTurn(player1, player2);
            if (player2.isLose()) break;
            playersTurn(player2, player1);
        }
        if (player1.isLose()) player2.congratulations();
        else player1.congratulations();
    }

    private void playersTurn(SimplePlayer me, SimplePlayer other) {
        boolean hit = true;
        displayStrategy.display(me, other);
        while (hit && !other.isLose()) {
            int[] coordinate = me.shoot();
            displayStrategy.accept(coordinate);
            int result = other.acceptDamage(coordinate[0], coordinate[1]);
            me.shootResultAction(result);
            displayStrategy.display(me, other);
            hit = result < 4;
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initPlayers() {
        iInput = new ConsoleInput(in, out);
        displayStrategy = new ConsoleDisplay(map, states, out, iInput);
        boolean reverse = false;
        String ip = null;
        int mode = displayStrategy.askMode();
        if (mode == 3) ip = displayStrategy.askIp();
        player1 = new HumanPlayer(10, "player1", displayStrategy);
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

            player2 = new NetPlayer(10, "netPlayer", new NetDisplay(consumer, supplier));
            ((NetPlayer) player2).setConsumer(consumer);
            ((NetPlayer) player2).setSupplier(supplier);
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


    private void initMaps() throws UnsupportedEncodingException {
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
        SingleGame game = new SingleGame(10);
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
                displayStrategy.accept("Waiting server connection");

                //заставим наш сервер ждать подключение какого-нибудь клиента
                Socket socket = srvSocket.accept();

                displayStrategy.accept("Connection succeed");

                InputStream sctIS = socket.getInputStream();
                OutputStream sctOS = socket.getOutputStream();

                initSupplierAndConsumer(sctIS, sctOS);

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
            //попытаемя подключиться к серверу
            try {
                InetAddress inetAddress = InetAddress.getByName(ip);
                System.out.printf("Connect to server: %d%n", servPort);

                //на стороне клиента так же необходим сокет
                Socket socket = new Socket(ip, servPort);

                InputStream sctIS = socket.getInputStream();
                OutputStream sctOS = socket.getOutputStream();

                //преобразуем байтовые стримы в DataStream
                initSupplierAndConsumer(sctIS, sctOS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initSupplierAndConsumer(InputStream inputStream, OutputStream outputStream) {
        DataInputStream inS = new DataInputStream(inputStream);
        DataOutputStream outS = new DataOutputStream(outputStream);

        //понадобится еще один поток, который будет считывать данные
        supplier = () -> {
            String rslt = null;
            try {
                rslt = inS.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rslt;
        };
        consumer = s -> {
            try {
                outS.writeUTF(s);
                outS.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}



