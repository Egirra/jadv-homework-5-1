import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 16346;

    public static void main(String[] args) {
        while (true) {
            try (Socket socket = new ServerSocket(PORT).accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()))) {

                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("end")) break;
                    int number = Integer.parseInt(line);
                    out.println("Результат: " + calculateFibonacciNumber(number));
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static int calculateFibonacciNumber(int number) {
        if (number == 0 || number == 1) {
            return number;
        } else if (number < 0) {
            return calculateFibonacciNumber(number + 2) - calculateFibonacciNumber(number + 1);
        } else {
            return calculateFibonacciNumber(number - 1) + calculateFibonacciNumber(number - 2);
        }
    }
}