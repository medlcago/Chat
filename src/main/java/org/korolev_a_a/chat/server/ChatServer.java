package org.korolev_a_a.chat.server;

import java.io.IOException;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerListener serverListener = new ServerListener(27015);
        serverListener.start();
    }
}
