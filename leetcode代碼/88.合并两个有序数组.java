/*
 * @lc app=leetcode.cn id=88 lang=java
 *
 * [88] 合并两个有序数组
 */

// @lc code=start
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i=m-1;
        int j=n-1;
        int cur=nums1.length-1;
        while(j>=0){
            if(i>=0&&nums1[i]>=nums2[j])
                nums1[cur--]=nums1[i--];
            else
                nums1[cur--]=nums2[j--];
        }

    }
}
// @lc code=end

