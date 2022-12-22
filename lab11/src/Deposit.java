public interface Deposit extends DepositParent {
    /**
     * TODO Антипаттерн Dependency hell
     */
    void info(Deposit deposit, String Name);

    double data_doll(double doll);
    double data_euro(double euro);
    double data_rub(double rub);
}
