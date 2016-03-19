package projects.Sorted;

/**
 * Created by Develop on 19.03.2016.
 */
public class ShellSort extends Sorting {
    public int[] myArray;
    @Override
    public void sortArray(int[] array) {
        myArray = array;
        int temp;
        int h = 1;
        while ( h <= myArray.length / 3) {
            h = h * 3 + 1;
        }

        while (h > 0) {

            for (int i = h; i < myArray.length; i++) {
                temp = myArray[i];
                int k = i;

                while (k > h - 1 && myArray[k - h] <= temp) {
                    myArray[k] = myArray[k - h];
                    k -= h;
                }
                myArray[k] = temp;
            }
            h = (h - 1) / 3;
        }
    }

    @Override
    public void printComplexityOfAlgorithm() {
        System.out.println("Сложность алгоритма " + myArray.length * Math.pow(Math.log10(myArray.length), 2) + " итераций");
    }
}
