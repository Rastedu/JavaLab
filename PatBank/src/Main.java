public class Main {
    public static void main(String[] args) {
        CreateDepositSber createDepSber = new CreateDepositSber();
        CreateDepositVuz createDepVuz = new CreateDepositVuz();
        createDepSber.factoryOfBank();
        createDepVuz.factoryOfBank();
        System.out.println("Изначальный вклад (в рублях): 44707");
        //createDepSber.get_data_doll(14345);
        //createDepSber.get_data_euro(15454);
        //createDepSber.get_data_rub(14558);
        //createDepVuz.get_data_doll(350);
        double s = createDepSber.get_data_doll(14345)*new Sber().currency_doll + createDepSber.get_data_euro(15454)*new Sber().currency_euro + createDepSber.get_data_rub(14558) + createDepVuz.get_data_doll(350)*new Vuz().currency_doll;
        System.out.println("Общая сумма по вкладам (в рублях):"+s);
    }
}