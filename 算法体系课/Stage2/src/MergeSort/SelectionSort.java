package MergeSort;

public class SelectionSort {
    private SelectionSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        //arr[0...i)是有序的
        for (int i = 0; i < arr.length; i++) {
            //选择arr[i..n)中的最小值的索引
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0)
                    minIndex = j;
            }
            swap(arr, i, minIndex);
        }
    }
    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    public static void main(String[] args) {
        int[] dataSize={1000,10000};
        for (int i : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(i,i);
            SortHelper.sortTest("SelectionSort",arr);
        }





//        Student[] students={new Student("bobo",98),
//                            new Student("alice",100),
//                            new Student("charles",60)};
//        SelectionSort.sort(students);
//        for (Student student : students) {
//            System.out.print(student + " ");
//            System.out.println();
//        }

    }
}
