
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8082);
           // Socket socke= new Socket("localhost", 8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Hello, world!");
            String response = in.readLine();
            System.out.println("Response: " + response);

            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    } 
}
