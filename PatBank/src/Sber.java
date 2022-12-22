public class Sber implements Deposit{
    private double percent_doll = 6;
    private double percent_euro = 8;
    private double percent_rub = 10;
    public double currency_doll = 62.91;
    public double currency_euro = 66.11;


    @Override
    public void info(Deposit deposit, String Name) {

    }

    @Override
    public double data_doll(double doll) {
         double s = (((percent_doll/100) + 1)*doll)/currency_doll;
        System.out.println("Через один год ставка по долларовому вкладу в Сбер банке составит(в долларах): "+s);
        return s;
    }

    @Override
    public double data_euro(double euro) {
        euro = (((percent_euro/100) + 1)*euro)/currency_euro;
        System.out.println("Через один год ставка по евро вкладу в Сбер банке составит(в евро): "+euro);
        return euro;
    }

    @Override
    public double data_rub(double rub) {
        rub = (percent_rub/100 + 1)*rub;
        System.out.println("Через один год ставка по рублевому вкладу в Сбер банке составит(в рублях): "+rub);
        return rub;
    }
}
