package com.leetcode.editor.cn;
//给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短
//序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。 
// 示例： 
// 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
//输出： [3,9]
// 
// 提示： 
// 
// 0 <= len(array) <= 1000000 
// 
// Related Topics 栈 贪心 数组 双指针 排序 单调栈 
// 👍 74 👎 0

public class P面试题 16.16SubSortLcci{
public static void main(String[]args){
        Solution solution=new P面试题16.16SubSortLcci().new Solution();
        // TO TEST
        }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] subSort(int[] array) {

        if (array.length == 0) return new int[]{-1, -1};
        //先从左向右查找
        int max = array[0];
        int r = -1;
        for (int i = 1; i <= array.length - 1; i++) {
            if (array[i] >= max) {
                max = array[i];
            } else {
                r = i;
            }
        }

        //提前结束
        // if (r == -1) return new int[]{-1, -1};

        //先从右向左查找
        int min = array[array.length - 1];
        int l = -1;
        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] <= min) {
                min = array[i];
            } else {
                l = i;
            }
        }

        return new int[]{l, r};


    }
}
//leetcode submit region end(Prohibit modification and deletion)

}