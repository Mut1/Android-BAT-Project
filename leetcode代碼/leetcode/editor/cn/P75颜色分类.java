package com.leetcode.editor.cn;
//给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 
//
// 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,0,1]
//输出：[0,1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[0]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以不使用代码库中的排序函数来解决这道题吗？ 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
// Related Topics 排序 数组 双指针 
// 👍 871 👎 0

public class P75SortColors {
    public static void main(String[] args) {
        Solution solution = new P75SortColors().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void sortColors(int[] nums) {

//        //利用三路快速排序 partition的思路
//        //arr[0...zero]==0 arr[zero+1,i]==1 arr[two,n-1]==2
//        int zero=-1,i=0,two= nums.length;
//        while (i<two){
//            if (nums[i]==0){
//                zero++;
//                swap(nums,zero,i);
//                i++;
//            }
//            else if (nums[i]==2){
//                two--;
//                swap(nums,i,two);
//            }
//            else
//                i++;
//        }

            //三指针
            int i = 0;
            int l = 0;
            int r = nums.length - 1;
            while (i <= r) {
                int v = nums[i];
                if (v == 0) {
                    swap(nums, l++, i++);

                } else if (v == 1) {
                    i++;
                } else {
                    swap(nums, i, r--);
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}