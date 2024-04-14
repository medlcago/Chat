package org.korolev_a_a.chat.server.listeners;

import org.korolev_a_a.chat.server.ChatLog;
import org.korolev_a_a.chat.server.handlers.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerListener {
    private final Integer port;
    private ServerSocket serverSocket;
    private static ArrayList<ClientHandler> clients;
    private ExecutorService executorService;

    public ServerListener(int port, int threadCount) {
        clients = new ArrayList<>();
        this.port = port;
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    public void start() throws IOException {

        serverSocket = new ServerSocket(this.port);
        System.out.println("Server started on port: " + this.port);

        ChatLog log = new ChatLog();

        while (true) {
            Socket incomingConnection = serverSocket.accept();
            ClientHandler client = new ClientHandler(incomingConnection, log);
            clients.add(client);
            executorService.execute(client);
        }
    }

    public static List<ClientHandler> getClients() {
        return clients;
    }

    public static void removeClient(ClientHandler clientHandler) {
        System.out.println("Client " + clientHandler + " left the chat");
        clients.remove(clientHandler);
    }
}