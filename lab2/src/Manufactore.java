import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Manufactore implements Runnable {
    BlockingQueue<String> queue = null;
    Memory memory;
    public boolean running = true;
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    public Manufactore(BlockingQueue<String> queue,Memory memory) {
        this.queue = queue;
        this.memory = memory;
    }

    @Override
    public void run() {
        int i=0;
        while (running) {
            i++;
            try {
                this.queue.put("Data["+i+"]");
                String text = Thread.currentThread().getName() + " is create data";
                System.out.println(text);

            } catch (InterruptedException ex) {
            }
        }
    }
    public void shutdown(){
        this.running = false;
    }
}
