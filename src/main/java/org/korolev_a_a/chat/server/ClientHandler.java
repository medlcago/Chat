package org.korolev_a_a.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    private final ChatLog chatLog;

    private BufferedReader in;

    private BufferedWriter out;

    private String nickName;

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            this.nickName = in.readLine();
            out.write("Welcome to the chat, " + nickName + "!\n");
            out.flush();

            chatLog.put(nickName + " connected to chat!", this);
            while (!Thread.currentThread().isInterrupted()) {
                String message = in.readLine();

                if (Objects.isNull(message)) {
                    break;
                }
                chatLog.put(nickName + ": " + message, this);
            }
            chatLog.put(nickName + " disconnected from chat!", this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerListener.removeClient(this);
    }

    public void sendMessageToClient(String msg) throws IOException {
        if (!clientSocket.isOutputShutdown()) {
            out.write(msg);
            out.newLine();
            out.flush();
        }
    }

    public ClientHandler(Socket clientSocket, ChatLog chatLog) {
        this.clientSocket = clientSocket;
        this.chatLog = chatLog;
    }

    @Override
    public String toString() {
        return nickName;
    }
}
