public class Sber implements Deposit{

    /**
     * TODO Антипаттерн Cryptic code
     * @param pdkl Persistance doll Kernel Load
     * @param pelk Persistance euro Kernel Load
     * @param prkl Persistance rub Kernel Load
     */
    private int pdkl = 6;
    private int pekl = 8;
    private int prkl = 10;

    /**
     * TODO Антипаттерн HardCoding
     * Так как курс может измениться и придётся менять переменные вручную.
     */
    public static final double sdad = 62.91;
    public static final double asda = 66.11;




    @Override
    public void info(Deposit deposit, String Name) {

    }

    @Override
    public double data_doll(double doll) {
        /**
         * TODO Антипаттерн Reinventing the square wheel
         * Вместо (((pdkl/100) + 1)*doll)/pdkl;
         * Используется длинная формула по подсчету процентов, которая учитывает ненужные в данной задаче свойства.
         */
        double s1 = (Math.pow((1+pdkl/(100*1)),(1*1))-1) * 100/1;
        double s2 = ((s1/100 + 1)*doll)/pdkl;
        return s2;
    }

    @Override
    public double data_euro(double euro) {
        euro = (((pekl/100) + 1)*euro)/pekl;
        return euro;
    }

    @Override
    public double data_rub(double rub) {
        rub = (prkl/100 + 1)*rub;
        return rub;
    }
    /**
     * TODO Антипаттерн Creeping featurism
     */
    public double[] innerSort(double[] integers) {
        double num;
        boolean isSorted = false;
        int i;
        while (!isSorted) {
            isSorted = true;
            /**
             * Сортировка в
             */
            for (i = 0; i < integers.length - 1; i++) {
                if (integers[i] > integers[i + 1]) {
                    num = integers[i];
                    integers[i] = integers[i + 1];
                    integers[i + 1] = num;
                    isSorted = false;
                }
            }
            for (; 0 < i; i--) {
                if (integers[i] < integers[i - 1]) {
                    num = integers[i];
                    integers[i] = integers[i - 1];
                    integers[i - 1] = num;
                    isSorted = false;
                }
            }
        }
        return integers;
    }
}
