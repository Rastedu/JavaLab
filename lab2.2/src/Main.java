import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        try {
            PipedOutputStream pos = new PipedOutputStream();
            PipedInputStream pis = new PipedInputStream(pos);
            Manufactore producer = new Manufactore(pos);
            User consumer = new User(pis);
            producer.start();
            consumer.start();
            producer.join();
            consumer.join();
            System.out.println("end of application");
            pos.close();
            pis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
}}