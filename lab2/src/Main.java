import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> q = new LinkedBlockingQueue<>(5);
        Memory memory = new Memory();
        Manufactore m = new Manufactore(q,memory);
        User u = new User(q,memory);
        Thread T = new Thread(m);
        Thread Y = new Thread(u);
        T.start();
        Y.start();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        m.shutdown();
        u.shutdown();
    }
}