package projects.Sorted;

/**
 * Created by Develop on 16.03.2016.
 */
public class SelectSort extends Sorting {
    public int[] myArray;
    @Override
    public void sortArray(int[] array) {
        myArray = array;
        for(int i = 0; i < myArray.length - 1; i++) {
            int max = i;
            for(int k = i+1; k < myArray.length; k++) {
                if (myArray[k] > myArray[max])
                    max = k;
            }
            swap(i, max);
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
