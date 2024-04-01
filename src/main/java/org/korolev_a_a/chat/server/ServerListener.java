package org.korolev_a_a.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerListener {
    private final Integer port;
    private ServerSocket serverSocket;
    private static ArrayList<ClientHandler> clients;

    public void start() throws IOException {

        serverSocket = new ServerSocket(this.port);
        System.out.println("Server started on port: " + this.port);

        ChatLog log = new ChatLog();

        while (true) {
            Socket incomingConnection = serverSocket.accept();
            ClientHandler client = new ClientHandler(incomingConnection, log);
            clients.add(client);
            Thread clientThread = new Thread(client);
            clientThread.start();
        }
    }

    public ServerListener(Integer port) {
        clients = new ArrayList<>();
        this.port = port;
    }

    public static List<ClientHandler> getClients() {
        return clients;
    }

    public static void removeClient(ClientHandler clientHandler) {
        System.out.println("Client " + clientHandler + " left the chat");
        clients.remove(clientHandler);
    }
}