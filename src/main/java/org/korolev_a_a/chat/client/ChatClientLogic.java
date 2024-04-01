package org.korolev_a_a.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ChatClientLogic {
    private final String serverHost;

    private final int serverPort;

    private final int threadCount;

    private ExecutorService executorService;

    public ChatClientLogic(String serverHost, int serverPort, int threadCount) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.threadCount = threadCount;
    }

    public void start() {
        try {
            Socket socket = new Socket(serverHost, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            executorService = Executors.newFixedThreadPool(threadCount);
            executorService.execute(new WriteThread(out));
            executorService.execute(new ReadThread(in));

        } catch (IOException e) {
            e.printStackTrace();
            shutdownExecutorService();
        } finally {
            shutdownExecutorService();
        }
    }

    private void shutdownExecutorService() {
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }
}
