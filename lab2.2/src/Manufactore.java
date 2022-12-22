import java.io.DataOutputStream;
import java.io.OutputStream;

class Manufactore extends Thread {
    // поток вывода любого типа
    private DataOutputStream dos;
    public Manufactore(OutputStream os) {
        dos = new DataOutputStream(os);
    }
    @Override
    public void run() {
        // что-то выводим в поток вывода
        String s = null;
        try {
            for (int i = 0; i < 20; ++i) {
                s = i + ": Hello, world";
                System.out.println("produce string " + s);
                dos.writeUTF(s);

                // засыпаем на 3 секунды для проверки ожидания потребителя
                if (i == 10) {
                    Thread.sleep(3000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end of producer thread");
    }
}
