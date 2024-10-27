package com.example;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;

public class MyThread extends Thread{
    Socket s;
    public MyThread (Socket s) {
        this.s = s;
    }

    public void run () {
        try {
            DataInputStream inputStream = new DataInputStream(s.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
            do {
                int sceltaRicevuta = inputStream.readInt();
                if (sceltaRicevuta == 5) {
                    System.err.println("Il client si è disconesso");
                    break;
                }
                String risposta = "";
                String stringaRicevuta = inputStream.readUTF();
                switch (sceltaRicevuta) {
                    case 1:
                        risposta = stringaRicevuta.toUpperCase();
                        break;
                
                    case 2:
                        risposta = stringaRicevuta.toLowerCase();
                        break;
                    case 3:
                        risposta = new StringBuilder(stringaRicevuta).reverse().toString();
                        break;
                    case 4:
                        int nCaratteri = stringaRicevuta.length();
                        risposta = Integer.toString(nCaratteri);
                        break;
                }
                outputStream.writeUTF(risposta);
            } while (true);
            inputStream.close();
            outputStream.close();
            s.close();
        } catch (Exception e) {

        }
    }
}
