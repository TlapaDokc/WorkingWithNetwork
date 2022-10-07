package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerByGame {
    private static final int port = 8989;
    private static String currentCity = "null";
    private static String newCity = "null";

    private static String checkingCity() {
        char lastChar = currentCity.toLowerCase().charAt(currentCity.length() - 1);
        char firstChar = newCity.toLowerCase().charAt(0);
        if (lastChar == firstChar) {
            currentCity = newCity;
            return "OK";
        } else {
            return "NOT OK";
        }
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                System.out.println("Server started");
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {// обработка одного подключения
                    System.out.println("New connection accepted");
                    if (currentCity.equals("null")) {
                        out.println("???");
                        currentCity = in.readLine();
                        System.out.println(currentCity);
                        out.println("ok");
                    } else {
                        out.println(currentCity);
                        newCity = in.readLine();
                        System.out.println(newCity);
                        out.println(checkingCity());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
