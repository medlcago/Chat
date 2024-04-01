package org.korolev_a_a.chat.client;

public class ChatClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 27015;
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) {
        ChatClientLogic chatClientLogic = new ChatClientLogic(SERVER_HOST, SERVER_PORT, THREAD_POOL_SIZE);
        chatClientLogic.start();
    }
}