public class CreateDepositVuz extends Creator{
    @Override
    protected Deposit factoryOfBank() {
        return new Vuz();
    }

    @Override
    protected double get_data_doll(double doll) {
        return new Vuz().data_doll(doll);
    }

    @Override
    protected double get_data_euro(double euro) {
        return new Vuz().data_euro(euro);
    }

    @Override
    protected double get_data_rub(double rub) {
        return new Vuz().data_rub(rub);
    }
}
