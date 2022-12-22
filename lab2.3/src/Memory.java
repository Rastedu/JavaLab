import static java.lang.Thread.*;
import static java.lang.Thread.sleep;
import java.lang.InterruptedException;

public class Memory {
        private int id = 0;
        public synchronized void get()
        {
            while (id < 1) try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Message ["+ (id-1)+ "] is received" );
            notify();
            id--;
        }
        public synchronized void put() {
            while (id > 2) {
                try {
                    wait();
                }catch (InterruptedException e) {System.out.println(e.getMessage());}
            }
            System.out.println("Message [" + id + "] is submit");
            notify();
            id++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
