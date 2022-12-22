
import static java.lang.Thread.*;
import static java.lang.Thread.sleep;
import java.lang.InterruptedException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.BlockingQueue;

public class Memory {
    BlockingQueue<String> queue;

    public Item get(BlockingQueue<Item> queue) {
        synchronized (queue) {
            try {
                if (queue.size() == 0) {
                    queue.wait(150);
                }

            } catch (Exception e) {
            }
            String text = Thread.currentThread().getName() + "is getting date";
            System.out.println(text);
            return queue.poll();
        }
    }

    public String put() {
        synchronized (queue) {
            String text = Thread.currentThread().getName() + "is receive date";
            System.out.println(text);
            queue.add(text);
            queue.notify();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.poll();
    }
}
