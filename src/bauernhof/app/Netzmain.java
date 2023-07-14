package bauernhof.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class was written by
 * @author Florian Will
 * @date 05.07.2023
 */

public class Netzmain {
    public static void main(String[] args) throws UnknownHostException, IOException {
        if (args.length < 1) {
            System.out.println("Bitte geben Sie die Anzahl der Clients an, die verbunden werden sollen.");
            return;
        }

        int numberOfClients = Integer.parseInt(args[0]);
        int Port = 1409;

        // Server
        if (args.length == 2) {
            try (ServerSocket serverSocket = new ServerSocket(Port)) {
                System.out.println("Server wartet auf Verbindung...");

                for (int i = 1; i <= numberOfClients; i++) {
                    Socket clientSocket = serverSocket.accept();
                }
            } catch (IOException e) {
                System.out.println("Fehler beim Akzeptieren der Verbindung: " + e.getMessage());
            }
        }

        // Client
        if (args.length == 3) {
            try (Socket ss = new Socket("127.0.0.1", Port)) {


            } catch (IOException e) {
                System.out.println("Fehler beim Herstellen der Verbindung zum Server: " + e.getMessage());
            }
        }
    }
}
