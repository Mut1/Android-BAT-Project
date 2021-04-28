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
        long endTime=System.nanoTime();
        if (!SortHelper.isSorted(arr))
            throw new RuntimeException( sortname+" Failed");
        System.out.print(String.format("%s , n= %d : %f s" ,sortname,arr.length, (endTime-startTime)/100000000.0));
        System.out.println();


    }
}
