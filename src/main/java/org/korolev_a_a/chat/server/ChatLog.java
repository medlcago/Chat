package org.korolev_a_a.chat.server;

import java.io.IOException;
import java.util.ArrayList;

public class ChatLog {

    private final ArrayList<String> chatHistory;

    private int pointer = 0;

    public void put(String message, ClientHandler clientSender) throws IOException {
        chatHistory.add(message);
        System.out.println(message);
        update(clientSender);
        pointer++;
    }

    private void update(ClientHandler clientSender) {
        ServerListener.getClients().forEach(client -> {
            if (client != clientSender) {
                try {
                    client.sendMessageToClient(chatHistory.get(pointer));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ChatLog() {
        chatHistory = new ArrayList<>();
    }

}