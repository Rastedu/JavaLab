import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Item> q = new LinkedBlockingQueue<>();
        Memory memory = new Memory();
        Manufactore m = new Manufactore(0, q, memory);
        User u = new User(0, q, memory);
        Manufactore m1 = new Manufactore(1, q, memory);
        Thread T = new Thread(m);
        Thread Y = new Thread(u);
        Thread T1 = new Thread(m1);
        T1.start();
        T.start();
        Y.start();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        // Enter, чтобы прервать поток
        m1.shutdown();
        m.shutdown();
        u.shutdown();
    }
}