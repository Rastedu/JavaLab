import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class User implements Runnable{
    Memory memory;
    private int id;
    private BlockingQueue<Item> queue;
    public boolean running = true;
    Random random = new Random();

    public User(int id, BlockingQueue<Item> queue,Memory memory) {
        this.id = id;
        this.queue = queue;
        this.memory=memory;
    }

    @Override
    public void run() {
        try {
            while (running) {
                try {
                    queue.take();
                    //memory.get();
                    System.out.println(Thread.currentThread().getName() + " is receive data["+queue.take().toString()+"]");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void shutdown(){
        this.running = false;
    }
}
