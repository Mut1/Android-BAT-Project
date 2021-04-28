package com.leetcode.editor.cn;
//在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 示例 1: 
//
// 输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 说明: 
//
// 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。 
// Related Topics 堆 分治算法 
// 👍 1051 👎 0

import java.util.Random;
import java.util.Arrays;
import java.math.BigInteger;
public class P215KthLargestElementInAnArray {
    public static void main(String[] args) {
        Solution solution = new P215KthLargestElementInAnArray().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findKthLargest(int[] nums, int k) {

            //selectK 找到的是第k小元素；找第k大的元素就是nums.length-k
            Random rnd = new Random();
            return selectK(nums, 0, nums.length-1,  nums.length - k, rnd);
        }

        //首先进行双路快速排序的partition，返回结果为p，p为该数组第p个小的元素，将k和p比较
        //如果k==p 直接返回值；如果k<p 在arr[l,p-1]的范围继续找；仍然是递归
        private int selectK(int[] arr, int l, int r, int k, Random rnd) {
            int p = partition(arr, l, r, rnd);
            if (k == p) return arr[p];
            if (k < p) return selectK(arr, l, p - 1, k, rnd);
            return selectK(arr, p + 1, r,k, rnd);
        }

        private int partition(int[] arr, int l, int r, Random rnd) {
            //生成[l,r]之间的随机索引
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