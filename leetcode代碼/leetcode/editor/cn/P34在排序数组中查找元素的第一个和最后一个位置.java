package com.leetcode.editor.cn;
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 1012 👎 0

public class P34FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new P34FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {

            int lower = lower(nums, target);
            int upper = upper(nums, target);
            if (lower + 1 < nums.length && nums[lower + 1] == target)
                lower = lower + 1;
            else
                lower = -1;
            if (upper - 1 >= 0 && nums[upper - 1] == target)
                upper = upper - 1;
            else
                upper = -1;

            return new int[]{lower, upper};
        }

        //<target的最大索引
        private int lower(int[] nums, int target) {
            int l = -1, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (nums[mid] < target)
                    l = mid;
                else r = mid - 1;
            }
            return l;
        }

        // >target的最小值索引
        private int upper(int[] nums, int target) {
            int l = 0, r = nums.length;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] <= target)
                    l = mid + 1;
                else r = mid;
            }
            return l;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}