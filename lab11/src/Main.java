import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    /**
     * TODO Антипаттерн Magic number
     */
    public static final int i1=5;

    public static void main(String[] args) {
        /**
         * TODO Антипаттерн Boat Anchor
         */
        int dep;
        /*BlockingQueue<Item> q = new LinkedBlockingQueue<>();
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
        */
        Scanner scanner = new Scanner(System.in);
        /**
         * TODO Антипаттерн Blind faith
         * Так как в случае, если пользователь введет некорректный тип вклада, программа выдаст критическую ошибку
         */
        System.out.println("Введите первый вклад в долларах");

        dep = scanner.nextInt();

        CreateDepositSber createDepSber = new CreateDepositSber();
        CreateDepositVuz createDepVuz = new CreateDepositVuz();
        createDepSber.factoryOfBank();
        createDepVuz.factoryOfBank();
        System.out.println("Изначальный вклад (в рублях): 44707");
        double[] deposits = new double[i1];
        for (int i=0;i<i1;i++){
            if(i==0){deposits[i] = createDepSber.get_data_doll(dep);}
            if(i==1){deposits[i] = createDepSber.get_data_euro(15454);}
            if(i==2){deposits[i] = createDepSber.get_data_rub(14558);}
            if(i==3){deposits[i] = createDepVuz.get_data_doll(350);}
        }
        System.out.println(Arrays.toString(createDepSber.innerSort(deposits)));
        //createDepSber.get_data_doll(14345);
        //createDepSber.get_data_euro(15454);
        //createDepSber.get_data_rub(14558);
        //createDepVuz.get_data_doll(350);

        double s = createDepSber.get_data_doll(14345)*new Sber().sdad + createDepSber.get_data_euro(15454)*new Sber().asda + createDepSber.get_data_rub(14558) + createDepVuz.get_data_doll(350)*(new Vuz().percent_doll+59.11);
        System.out.println("Общая сумма по вкладам (в рублях):"+s);
    }
}