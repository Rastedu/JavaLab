import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Manufactore implements Runnable {
    Memory memory;
    private int id;
    private BlockingQueue<Item> queue;
    public boolean running = true;
    Random random = new Random();

    public Manufactore(int id, BlockingQueue<Item> queue,Memory memory) {
        this.id = id;
        this.queue = queue;
        this.memory=memory;
    }

    @Override
    public void run() {
        int i=0;
        Item item = new Item(i);
        while(running) {
            try {
                queue.put(item);
                //memory.put();
                System.out.println(Thread.currentThread().getName() + " is create data["+item+"]");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        try {
            queue.put(new Item(-1));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void shutdown(){
        this.running = false;
    }
}
