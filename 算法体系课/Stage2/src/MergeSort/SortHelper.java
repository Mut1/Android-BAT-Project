package MergeSort;

import QuickSort.QuickSort;

public class SortHelper {
    private SortHelper() {
    }

    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i - 1].compareTo(arr[i]) > 0)
                return false;
        return true;
    }

    public static <E extends Comparable<E>> void sortTest(String sortname,E[] arr){

        long startTime=System.nanoTime();
        if (sortname.equals("SelectionSort"))
            SelectionSort.sort(arr);
        if (sortname.equals("InsertionSort"))
            InsertionSort.sort(arr);
        if (sortname.equals("MergeSort"))
            MergeSort.sort(arr);
        if (sortname.equals("MergeSort2"))
            MergeSort.sort2(arr);
        if (sortname.equals("MergeSort3"))
            MergeSort.sort3(arr);
        if (sortname.equals("MergeSort4"))
            MergeSort.sort4(arr);
        if (sortname.equals("MergeSortBU"))
            MergeSort.sortBU(arr);
        if (sortname.equals("QuickSort"))
            QuickSort.sort(arr);
        if (sortname.equals("QuickSort2ways"))
            QuickSort.sort2ways(arr);
        if (sortname.equals("QuickSort3ways"))
            QuickSort.sort3ways(arr);
        long endTime=System.nanoTime();
        if (!SortHelper.isSorted(arr))
            throw new RuntimeException( sortname+" Failed");
        System.out.print(String.format("%s , n= %d : %f s" ,sortname,arr.length, (endTime-startTime)/100000000.0));
        System.out.println();


    }
}
