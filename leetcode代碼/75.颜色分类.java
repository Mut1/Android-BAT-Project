/*
 * @lc app=leetcode.cn id=75 lang=java
 *
 * [75] 颜色分类
 */

// @lc code=start
class Solution {
    public void sortColors(int[] nums) {

        int l=0;
        int i=0;
        int r=nums.length-1;
    
        while(i<=r){
            int v=nums[i];
            if(v==0){
                swap(nums, l++, i++);
                
            }else if(v==1){
                i++;
            }else{
                swap(nums, i, r--);
            }
        }

    }

    public void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
// @lc code=end

