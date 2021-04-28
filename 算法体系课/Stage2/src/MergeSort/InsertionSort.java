package MergeSort;

import java.util.Arrays;

public class InsertionSort {
    private InsertionSort() {

    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            E t=arr[i];
            int j;
            for (j = i; j - 1 >= 0 && t.compareTo(arr[j - 1]) < 0; j--)
             arr[j]=arr[j-1];
            arr[j]=t;
        }
    }
    public static <E extends Comparable<E>> void sort(E[] arr,int l,int r) {
        for (int i = l; i <= r; i++) {
            E t=arr[i];
            int j;
            for (j = i; j - 1 >= l && t.compareTo(arr[j - 1]) < 0; j--)
                arr[j]=arr[j-1];
            arr[j]=t;
        }
    }
    public static void main(String[] args) {
        int[] dataSize = {1000, 10000};
        for (int i : dataSize) {
            System.out.println("Random Array:");
            Integer[] arr = ArrayGenerator.generateRandomArray(i, i);
            Integer[] arr2= Arrays.copyOf(arr,arr.length);
            SortHelper.sortTest("InsertionSort", arr);
            SortHelper.sortTest("SelectionSort", arr2);
            System.out.println();

            System.out.println("Ordered Array:");
            Integer[] arr3 = ArrayGenerator.generateOrderArray(i);
            Integer[] arr4= Arrays.copyOf(arr,arr.length);
            SortHelper.sortTest("InsertionSort", arr);
            SortHelper.sortTest("SelectionSort", arr2);
            System.out.println();

        }
    }
}

