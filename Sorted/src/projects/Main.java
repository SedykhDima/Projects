package projects;

import projects.Sorted.AbstractSorted;
import projects.Sorted.BubbleSort;
import projects.Sorted.InsertSort;
import projects.Sorted.SelectSort;

/**
 * Created by Develop on 16.03.2016.
 */
public class Main {
    public static int[] mas = new int[]{754, 503, 562, 206, 654, 332, 935, 824, 851, 221, 510, 330, 670, 253, 694,
            79, 620, 950, 730, 184, 217, 695, 289, 829, 289, 218, 169, 837, 964, 706, 653, 447, 346, 774, 713,
            701, 5, 226, 936, 943, 569, 132, 398, 24, 639, 639, 221, 377, 862, 592, 864, 387, 730, 849, 196, 132,
            441, 916, 751, 640, 537, 695, 625, 316, 331, 156, 928, 130, 852, 874, 795, 587, 427, 273, 823, 362, 179,
            991, 68, 691, 609, 767, 709, 847, 93, 369, 943, 99, 74, 376, 679, 967, 335, 88, 973, 328, 640, 719, 812, 60};
    public static void main(String[] args) throws InterruptedException {
        printInfoSort(new InsertSort());
        printInfoSort(new SelectSort());
        printInfoSort(new BubbleSort());
    }

    public static void printResult(int[] array) {
        for (int a : array) {
            System.out.print(a+", ");
        }
    }

    public static void printInfoSort(AbstractSorted abstractSorted) {
        abstractSorted.printTimeSorted(mas.clone());
        abstractSorted.printComplexityOfAlgorithm();
        printResult(abstractSorted.myArray);
        System.out.println("\n");
    }

}
