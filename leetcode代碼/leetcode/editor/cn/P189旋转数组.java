package com.leetcode.editor.cn;
//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 
//
// 进阶： 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？ 
// 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4,5,6,7], k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 
//输入：nums = [-1,-100,3,99], k = 2
//输出：[3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// -231 <= nums[i] <= 231 - 1 
// 0 <= k <= 105 
// 
//
// 
// 
// Related Topics 数组 
// 👍 941 👎 0

public class P189RotateArray{
    public static void main(String[] args) {
        Solution solution = new P189RotateArray().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void rotate(int[] nums, int k) {

        k %= nums.length;
        reverse(nums,0,nums.length-1);//反转第一次
        reverse(nums,0,k-1);//反转第二次
        reverse(nums,k,nums.length-1);//反转第san次


    }

    public int[] reverse(int[] nums,int i,int j)
    {
        while (i<j){
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
            i++;
            j--;
        }

        return nums;
    }

//        public void rotate(int[] nums, int k) {
//
//        int[] newNums=new int[nums.length];
//            for (int i = 0; i < nums.length; i++) {
//                newNums[(i+k)%nums.length]=nums[i];
//            }
//            System.arraycopy(newNums,0,nums,0,nums.length);
//
//        }


}
//leetcode submit region end(Prohibit modification and deletion)

}