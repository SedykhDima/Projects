package projects.Sorted;

/**
 * Created by Develop on 16.03.2016.
 */
public abstract class Sorting {
    public int[] myArray;
    public abstract void sortArray(int[] array);

    public void printTimeSorted(int[] array) {
        myArray = array;
        long before = System.nanoTime();
        sortArray(myArray);
        long after = System.nanoTime();
        System.out.println(this.getClass().getSimpleName() + " выполняется за: "+ (after - before) + " nanoseconds");
    }

    public abstract void printComplexityOfAlgorithm();
}
