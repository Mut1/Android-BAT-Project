package com.leetcode.editor.cn;
//设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。 
//
// 示例： 
//
// 输入： arr = [1,3,5,7,2,4,6,8], k = 4
//输出： [1,2,3,4]
// 
//
// 提示： 
//
// 
// 0 <= len(arr) <= 100000 
// 0 <= k <= min(100000, len(arr)) 
// 
// Related Topics 堆 排序 分治算法 
// 👍 58 👎 0

import java.util.Arrays;
import java.util.Random;

public class P面试题 17.14SmallestKLcci{
public static void main(String[]args){
        Solution solution=new P面试题17.14SmallestKLcci().new Solution();
        // TO TEST
        }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] smallestK(int[] arr, int k) {

        //在arr[1...r]的范围里求解整个数组的第k个小元素并返回
        //k是索引，即从0开始计算
        if(k==0) return new int[0];
        Random rnd=new Random();
        selectK(arr,0,arr.length-1,k-1,rnd);
        return Arrays.copyOf(arr, k);


    }

    private int selectK(int[] arr, int l, int r, int k, Random rnd) {
        int p = partition(arr, l, r, rnd);
        if (k == p) return arr[p];
        if (k < p) return selectK(arr, l, p - 1, k, rnd);
        return selectK(arr, p + 1, r,k, rnd);
    }

    private int partition(int[] arr, int l, int r, Random rnd) {
        //生成[1,r]之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);
        //arr[l+1...i-1]<=v arr[j+1...r]>=v
        int i = l + 1, j = r;
        while (true) {
            while (i <= j && arr[i] < arr[l])
                i++;
            while (j >= i && arr[j] > arr[l])
                j--;
            if (i >= j) break;
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}