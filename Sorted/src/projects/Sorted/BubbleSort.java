package projects.Sorted;

/**
 * Created by Develop on 16.03.2016.
 */
public class BubbleSort extends Sorting {
    public int myArray[];
    @Override
    public void sortArray(int[] array) {
        this.myArray = array;
        for (int i = myArray.length - 1; i > 0; i--) {
            for (int k = 0; k < i; k++) {
                if (myArray[i] > myArray[k]) {
                    swap(i, k);
                }
            }
        }
    }
    private void swap(int one, int two) {
        int temp = myArray[one];
        myArray[one] = myArray[two];
        myArray[two] = temp;
    }

    @Override
    public void printComplexityOfAlgorithm() {
        System.out.println("Сложность алгоритма " + Math.pow(myArray.length, 2) + " итераций");
    }
}
