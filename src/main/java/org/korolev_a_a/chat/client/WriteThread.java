package org.korolev_a_a.chat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class WriteThread implements Runnable {
    private BufferedWriter out;

    public WriteThread(BufferedWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        try {
            BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String message = keyboardIn.readLine();
                out.write(message);
                out.newLine();
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}