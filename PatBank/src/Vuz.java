public class Vuz implements Deposit {
    private double percent_doll = 6;
    public double currency_doll = 63.11;
    public double currency_euro = 65.11;

    @Override
    public void info(Deposit deposit, String Name) {

    }

    @Override
    public double data_doll(double doll) {
        doll = (((percent_doll/100) +1)*doll)/currency_doll;
        System.out.println("Через один год ставка по долларовому вкладу в Вуз банке станет(в долларах): "+doll);
        return doll;
    }

    @Override
    public double data_euro(double euro) {
        System.out.println("Извините, но банк временно не обслуживает евро депозиты.");
        return 0;
    }

    @Override
    public double data_rub(double rub) {
        System.out.println("Извините, но банк временно не обслуживает рублевые депозиты.");
        return 0;
    }
}
