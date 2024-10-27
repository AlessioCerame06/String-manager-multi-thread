package com.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Il client è partito");
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Socket mySocket = new Socket("localhost", 3000);
        System.out.println("Il client si è collegato al server" + "\n");
        DataOutputStream outputStream = new DataOutputStream(mySocket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(mySocket.getInputStream());

        int scelta;
        String str = "";
        do {
            System.out.println("*****************MENU*****************");
            System.out.println("1) Trasformare stringa in maiuscolo");
            System.out.println("2) Trasformare stringa in minuscolo");
            System.out.println("3) Ribaltare i caratteri della stringa");
            System.out.println("4) Contare i numeri dei caratteri");
            System.out.println("5) Disconessione");
            System.out.println("Scegliere un comando");
            do {
                scelta = scanner1.nextInt();
                if (scelta < 1 || scelta > 5) {
                    System.out.println("Errore. Il comando " + scelta + " non esiste. Scrivere un comando esistente.");
                }
            } while (scelta < 1 || scelta > 5);
            if (scelta != 5) {
                System.out.println("Scrivere una stringa");
                str = scanner2.nextLine();
                outputStream.writeInt(scelta);
                outputStream.writeUTF(str);
                String stringaRicevuta = inputStream.readUTF();
                System.out.println("Risultato: " + stringaRicevuta);
            } else {
                outputStream.writeInt(scelta);
                System.out.println("Disconessione avvenuta");
            }
        } while (scelta != 5);
        outputStream.close();
        inputStream.close();
        mySocket.close();
        scanner1.close();
        scanner2.close();
    }
}