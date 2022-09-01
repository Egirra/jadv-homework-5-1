import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    // почему Blocking IO: выбираем данный способ взаимодействия, т.к. нам нужен только конечный, а не промежуточный результат

    private static final int PORT = 16346;
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORT);
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String msg;
            System.out.println("Добро пожаловать на \"Сервер Фибоначчи\"");
            while (true) {
                System.out.println("Введи число для вычисления \nДля завершения введи end");
                msg = scanner.nextLine();
                out.println(msg);
                System.out.println(in.readLine());
                if (msg.equals("end")) break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}