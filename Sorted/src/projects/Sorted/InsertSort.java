package projects.Sorted;

/**
 * Created by Develop on 16.03.2016.
 */
public class InsertSort extends Sorting {
    public int[] myArray;
    @Override
    public void sortArray(int[] array) {
        myArray = array;
        for (int i = 1; i < myArray.length; i++) {
            int temp = myArray[i];
            int k = i;
            while (k > 0 && myArray[k - 1] <= temp) {
                myArray[k] = myArray[k - 1];
                --k;
            }
            myArray[k] = temp;
        }
    }

    @Override
    public void printComplexityOfAlgorithm() {
        System.out.println("Сложность алгоритма " + Math.pow(myArray.length, 2) + " итераций");
    }
}
