package com.leetcode.editor.cn;
//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。 
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
// 👍 402 👎 0

public class P剑指 Offer 51ShuZuZhongDeNiXuDuiLcof{
public static void main(String[]args){
        Solution solution=new P剑指 Offer 51ShuZuZhongDeNiXuDuiLcof().new Solution();
        // TO TEST
        }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int res = 0;

    public int reversePairs(int[] nums) {
        int[] temp = new int[nums.length];
        res = 0;
        sort(nums, 0, nums.length-1, temp);
        return res;
    }

    private void sort(int[] arr, int l, int r, int[] temp) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        sort(arr, l, mid, temp);
        sort(arr, mid + 1, r, temp);
        //因为arr[l,mid] arr[mid+1,r]已经有序了
        if (arr[mid] > arr[mid + 1])
            merge(arr, l, mid, r, temp);
    }

    //合并两个有序的区间arr[l,mid]和arr[mid+1,r]
    private void merge(int[] arr, int l, int mid, int r, int[] temp) {
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
            } else if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                res += mid - i + 1;
                arr[k] = temp[j];
                j++;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}