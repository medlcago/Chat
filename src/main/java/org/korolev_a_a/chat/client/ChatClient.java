package org.korolev_a_a.chat.client;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 27015;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Thread writeThread = new Thread(new WriteThread(out));
            Thread readThread = new Thread(new ReadThread(in));

            writeThread.start();
            readThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}