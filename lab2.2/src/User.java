import java.io.DataInputStream;
import java.io.InputStream;

public class User extends Thread {
    // поток ввода любого типа
    private DataInputStream dis;
    public User(InputStream is) {
        dis = new DataInputStream(is);
    }
    @Override
    public void run() {
        // принимаем данные
        try {
            for (int i = 0; i < 20; ++i) {
                System.out.println("consume string " + dis.readUTF());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end of consumer thread");
    }
}