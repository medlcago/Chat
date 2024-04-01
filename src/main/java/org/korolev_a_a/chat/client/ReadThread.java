package org.korolev_a_a.chat.client;

import java.io.BufferedReader;
import java.io.IOException;

class ReadThread implements Runnable {
    private BufferedReader in;

    public ReadThread(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = in.readLine();
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
