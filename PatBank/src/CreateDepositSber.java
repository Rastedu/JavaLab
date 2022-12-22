public class CreateDepositSber extends Creator{
    @Override
    protected Sber factoryOfBank() {
        return new Sber();
    }

    @Override
    protected double get_data_doll(double doll) {
        return new Sber().data_doll(doll);
    }

    @Override
    protected double get_data_euro(double euro) {
        return new Sber().data_euro(euro);
    }

    @Override
    protected double get_data_rub(double rub) {
        return new Sber().data_rub(rub);
    }

}
