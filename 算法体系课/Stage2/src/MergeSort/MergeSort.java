package MergeSort;
import java.util.Arrays;

public class MergeSort {
    private MergeSort() {
    }

    //自顶向下的归并排序
    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    //自顶向下的归并排序
    //常用
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        sort2(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort2(E[] arr, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        sort2(arr, l, mid);
        sort2(arr, mid + 1, r);
        //因为arr[l,mid] arr[mid+1,r]已经有序了
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, l, mid, r);
    }

    //自顶向下的归并排序
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        sort3(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort3(E[] arr, int l, int r) {
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }
        int mid = l + (r - l) / 2;
        sort3(arr, l, mid);
        sort3(arr, mid + 1, r);
        //因为arr[l,mid] arr[mid+1,r]已经有序了
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, l, mid, r);
    }

    //自顶向下的归并排序
    //内存优化
    public static <E extends Comparable<E>> void sort4(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        sort4(arr, 0, arr.length - 1, temp);
    }

    private static <E extends Comparable<E>> void sort4(E[] arr, int l, int r, E[] temp) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        sort4(arr, l, mid, temp);
        sort4(arr, mid + 1, r, temp);
        //因为arr[l,mid] arr[mid+1,r]已经有序了
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge2(arr, l, mid, r, temp);
    }

    //自底向上的归并排序
    public static <E extends Comparable<E>> void sortBU(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;
        //遍历合并的区间长度
        for (int sz = 1; sz < n; sz += sz) {
            //遍历合并的两个区间的起始位置i
            //合并[i,i+sz-1] [i+sz,Math.min(i+sz+sz-1,n-1)]
            for (int i = 0; i + sz < n; i += sz + sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0)
                    merge2(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), temp);
            }

        }
    }

    //合并两个有序的区间arr[l,mid]和arr[mid+1,r]
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);//copyOfRange前闭后开
        int i = l, j = mid + 1;

        //每轮循环为arr[k]赋值
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = temp[j - l];
                j++;
            } else if (j > r) {
                arr[k] = temp[i - l];
                i++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i - l];
                i++;
            } else {
                arr[k] = temp[j - l];
                j++;
            }
        }
    }

    //内存优化
    //合并两个有序的区间arr[l,mid]和arr[mid+1,r]
    private static <E extends Comparable<E>> void merge2(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r - l + 1);
        int i = l, j = mid + 1;

        //每轮循环为arr[k]赋值
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = temp[j];
                j++;
            } else if (j > r) {
                arr[k] = temp[i];
                i++;
            } else if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        // Integer[] arr = ArrayGenerator.generateOrderArray(n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);
        SortHelper.sortTest("MergeSort", arr);
        SortHelper.sortTest("MergeSort2", arr2);
        SortHelper.sortTest("MergeSort3", arr3);
        SortHelper.sortTest("MergeSort4", arr4);
        SortHelper.sortTest("MergeSortBU", arr5);
        // SortHelper.sortTest("SelectionSort", arr3);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, int depth) {

        // 生成深度字符串
        String depthString = generateDepthString(depth);

        // 打印当前 sort 处理的数组区间信息
        System.out.print(depthString);
        System.out.println(String.format("mergesort arr[%d, %d]", l, r));

        if (l >= r)
            return;

        int mid = l + (r - l) / 2;
        sort(arr, l, mid, depth + 1);
        sort(arr, mid + 1, r, depth + 1);

        // 打印这次 merge 要处理的区间范围
        System.out.print(depthString);
        System.out.println(String.format("merge arr[%d, %d] and arr[%d, %d]", l, mid, mid + 1, r));

        merge(arr, l, mid, r);

        // 打印 merge 后的数组
        System.out.print(depthString);
        System.out.print(String.format("after mergesort arr[%d, %d] :", l, r));
        for (E e : arr)
            System.out.print(e + " ");
        System.out.println();
    }

    private static String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }


}
