/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replicatedcomponent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author wku-cslab1
 */
public class ReplicatedComponent implements Runnable{

       private final int port;
    private final String primaryAddr;
    private final String backupAddr;

    public ReplicatedComponent(int port, String primaryAddr, String backupAddr) {
        this.port = port;
        this.primaryAddr = primaryAddr;
        this.backupAddr = backupAddr;
    }

    public void run() {
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String input = in.readLine();
                out.println(input);

                clientSocket.close();
               serverSocket.close();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String primaryAddr = "localhost:8080";
        String backupAddr = "localhost:8081";

       // new Thread(new ReplicatedComponent(8080, primaryAddr, backupAddr)).start();
        new Thread(new ReplicatedComponent(8081, backupAddr, primaryAddr)).start();
        
    }
}
