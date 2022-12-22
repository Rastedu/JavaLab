import java.util.concurrent.BlockingQueue;

public class User implements Runnable {
    BlockingQueue<String> Blockqueue = null;
    Memory memory;

    public boolean running = true;
    public static int id = 0;

    public User(BlockingQueue<String> q,Memory memory) {
        this.Blockqueue = q;
        this.memory = memory;
    }

    private void use(Object take){

        }


    public void run() {
    while (running) {

        try {
            String text = this.Blockqueue.take() + Thread.currentThread().getName() + " is receive data";
            System.out.println(text);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
    public void shutdown(){
        this.running = false;
    }
}