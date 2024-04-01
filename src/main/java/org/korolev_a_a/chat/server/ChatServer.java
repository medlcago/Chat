package org.korolev_a_a.chat.server;

import java.io.IOException;

public class ChatServer {
    private static final int SERVER_PORT = 27015;
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) throws IOException {
        ServerListener serverListener = new ServerListener(SERVER_PORT, THREAD_POOL_SIZE);
        serverListener.start();
    }
}
