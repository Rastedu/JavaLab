import java.math.BigInteger;

public class Vuz implements Deposit, Stub {

    /**
     * TODO Антипаттерн GodObject
     * @param percent_doll
     */
    public double percent_doll = 6;

    /**
     * TODO Антипаттерн CtrlC+CtrlV
     */

    // дробная часть, необходимо увеличить для повышения точности
    static BigInteger multiplier = new BigInteger("1000");

    // 2%
    static BigInteger divider = new BigInteger("50");

    public static void dep() {

        BigInteger deposit = new BigInteger("100000").multiply(multiplier); // 1000.00 $

        for (int year = 1; year <= 5; year++) {
            deposit = deposit.add(deposit.divide(divider));
            log(year, deposit);
        }
    }

    public static void log(int year, BigInteger deposit) {
        BigInteger amount = deposit.divide(multiplier);
        System.out.println("За " + year + " год, на вашем счету появилась " + amount + " сумма центов");
    }

    @Override
    public void info(Deposit deposit, String Name) {

    }

    @Override
    public double data_doll(double doll) {
        doll = (((percent_doll/100) +1)*doll)/(percent_doll+57.11);
        return doll;
    }

    @Override
    public double data_euro(double euro) {
        euro = (((percent_doll/100) +1)*euro)/(percent_doll+59.11);
        return 0;
    }

    @Override
    public double data_rub(double rub) {
        rub = (((percent_doll/100) +1)*rub);
        return 0;
    }

    /**
     * TODO Антипаттерн Спагетти-код
     * @param source
     * @param leftBorder
     * @param rightBorder
     */
    public static void quickSort(int[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = source[(leftMarker + rightMarker) / 2];
        do {

            while (source[leftMarker] < pivot) {
                leftMarker++;
            }

            while (source[rightMarker] > pivot) {
                rightMarker--;
            }

            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    int tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        if (leftMarker < rightBorder) {
            quickSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(source, leftBorder, rightMarker);
        }

        int[] array = {10, 2, 10, 3, 1, 2, 5};
        int gap = array.length / 2;
        while (gap >= 1) {
            for (int right = 0; right < array.length; right++) {

                for (int c = right - gap; c >= 0; c -= gap) {
                    if (array[c] > array[c + gap]) {
                        swap(array, c, c + gap);
                    }
                }
            }
            gap = gap / 2;
        }

        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                swap(array, i, i - 1);
                for (int z = i - 1; (z - 1) >= 0; z--) {
                    if (array[z] < array[z - 1]) {
                        swap(array, z, z - 1);
                    } else {
                        break;
                    }
                }
            }
        }


        boolean isSwapped = true;
        int start = 0;
        int end = array.length;

        while (isSwapped == true) {
            isSwapped = false;
            for (int i = start; i < end - 1; ++i) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    isSwapped = true;
                }
            }
            if (isSwapped == false)
                break;
            isSwapped = false;
            end = end - 1;

            for (int i = end - 1; i >= start; i--) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    isSwapped = true;
                }
            }
            start = start + 1;
        }
    }

    private static void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    @Override
    public void sort() {

    }

    @Override
    public void max() {

    }

    @Override
    public void min() {

    }
}
